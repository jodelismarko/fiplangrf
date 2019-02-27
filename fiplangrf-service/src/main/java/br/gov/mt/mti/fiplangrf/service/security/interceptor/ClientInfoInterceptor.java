package br.gov.mt.mti.fiplangrf.service.security.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.cepromat.ceprofw.core.interceptor.ClientInfoOracleUtil;
import br.gov.mt.cepromat.ceprofw.core.service.annotation.OperationType;
import br.gov.mt.cepromat.ceprofw.core.service.annotation.SystemBusinessPermission;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.service.security.GenericService;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;
import br.gov.mt.mti.fiplangrf.service.security.exception.LogoutException;
import br.gov.mt.mti.fiplangrf.service.security.exception.PermissaoNegadaException;

public class ClientInfoInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9003275394028643451L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientInfoInterceptor.class);

	private static final String SYSTEM_USER = "fiplangrf-POOL-DB";

	private Map<String, Boolean> nonRestrictedMethods = new HashMap<>();

	private static final String METHOD_AUTHENTICATE = "br.gov.mt.mti.fiplangrf.service.security.user.UsuarioLoginService.findByLogin";

	{
		this.nonRestrictedMethods.put(METHOD_AUTHENTICATE, Boolean.TRUE);
		this.nonRestrictedMethods.put(
				Constantes.BASE_PACKAGE + ".service.security.user.UsuarioLoginService.updateDataLogin", Boolean.TRUE);
		this.nonRestrictedMethods.put(
				Constantes.BASE_PACKAGE + ".service.security.user.UsuarioLoginService.updateDataLoginNULL",
				Boolean.TRUE);
	}

	@PersistenceContext
	protected EntityManager em;

	@EJB
	private GenericService genericService;

	@AroundInvoke
	public Object setClientInfo(InvocationContext context) throws Exception {

		UsuarioLogado usuarioLogado = Env.getUsuarioLogado();

		if (EnvUtil.getAmbiente().equals("des")) {
			return context.proceed();
		}

		String clientInfo = SYSTEM_USER;

		TransactionAttribute transactionAttribute = context.getMethod().getAnnotation(TransactionAttribute.class);
		boolean isNotNestedTransaction = true;
		if (transactionAttribute != null) {
			if (transactionAttribute.value().equals(TransactionAttributeType.MANDATORY)) {
				isNotNestedTransaction = false;
			}
		}
		Object obj = null;
		if (isNotNestedTransaction) {
			if (usuarioLogado != null) {
				clientInfo = usuarioLogado.getCpf();
				if (!genericService.isLoggedIn(usuarioLogado)) {
					usuarioLogado.setExpired(true);
					throw new LogoutException();
				}
			}

			ClientInfoOracleUtil.setClientInfo(em, clientInfo);
			ClientInfoOracleUtil.setModuleAction(em, getClassName(context), context.getMethod().getName() + "#"
					+ (usuarioLogado != null ? usuarioLogado.getAction() : "notAutenticated"));

			obj = invoke(usuarioLogado, context);
		} else {
			/*
			 * Métodos que controlam saldo já instrumentam a sessão, portanto não precisam
			 * passar por método deste interceptor.
			 */
			obj = invoke(usuarioLogado, context);
		}
		return obj;
	}

	private boolean isMethodNonRestricted(String method) {

		return this.nonRestrictedMethods.containsKey(method);
	}

//	private Method getMethod(InvocationContext context) throws Exception {
//		
//		Object[] parameters = context.getParameters();
//		
//		Class<?> classTarget = Class.forName(getClassFullName(context));		
//		
//		Method m = null;
//		
//		if(parameters == null){
//			m = classTarget.getMethod(getMethodName(context));	
//		} else {
//			Class<?>[] parameterTypes = new Class<?>[parameters.length];
//			for(int i=0; i<parameters.length; i++){
//				parameterTypes[i] = parameters[i].getClass();
//			}
//			m = MethodUtils.getMatchingAccessibleMethod(classTarget, getMethodName(context), parameterTypes);
//		}		
//		return m;
//	}

	private String getClassName(InvocationContext context) throws Exception {
		String clazzFullName = context.getTarget().toString();
		int lastIndexPeriod = clazzFullName.lastIndexOf(".");
		int lastIndexAt = clazzFullName.lastIndexOf("@");
		String clazzName = clazzFullName.substring(lastIndexPeriod + 1, lastIndexAt);
		return clazzName;
	}

	private String getClassFullName(InvocationContext context) throws Exception {
		String clazzFullName = context.getTarget().toString();
		int lastIndexAt = clazzFullName.lastIndexOf("@");
		String clazzName = clazzFullName.substring(0, lastIndexAt);
		return clazzName;
	}

	private String getFullMethodName(InvocationContext context) throws Exception {

		return getClassFullName(context) + "." + context.getMethod().getName();
	}

	private Object invoke(UsuarioLogado usuarioLogado, InvocationContext context) throws Exception {

		if (EnvUtil.getAmbiente().equals("des")) {
			return context.proceed();
		}

		String className = getClassFullName(context);

		Class<?> classTarget = Class.forName(className);

		boolean isClassLevelAnnotationPresent = classTarget.isAnnotationPresent(CheckBusinessAccess.class);

		/*
		 * Para que a verificação de permissão não ocorra no EJB, esta configuração
		 * deverá ser explicitamente configurada.
		 */
		if (isClassLevelAnnotationPresent) {
			CheckBusinessAccess classLevel = classTarget.getAnnotation(CheckBusinessAccess.class);
			if (!classLevel.check()) {
				return context.proceed();
			}
		}

		String fullMethodName = getFullMethodName(context);

		Method m = context.getMethod();

		boolean isAnnotationMethodLevelPresent = m.isAnnotationPresent(CheckBusinessAccess.class);

		/*
		 * Se a anotação está presente no método, esta anotação prevalecerá sobre a da
		 * classe
		 */
		if (!isClassLevelAnnotationPresent && isAnnotationMethodLevelPresent) {
			CheckBusinessAccess methodLevel = m.getAnnotation(CheckBusinessAccess.class);
			if (methodLevel.check()) {
				return verificarPermissoes(classTarget, context);
			} else {
				return context.proceed();
			}
		}
		/*
		 * Se não houver anotação nenhuma, checar o método. Por padrão, TODOS os métodos
		 * serão verificados a não ser que seja explicitamente liberado da checagem.
		 */
		SystemBusinessPermission systemBusinessPermission = null;
		if (context.getMethod().isAnnotationPresent(SystemBusinessPermission.class)) {
			systemBusinessPermission = context.getMethod().getAnnotation(SystemBusinessPermission.class);
		}
		if (systemBusinessPermission != null) {
			String systemPermissionCRUD = getClassFullName(context) + "."
					+ systemBusinessPermission.operation().toString().toLowerCase();
			if (checarPermissao(usuarioLogado, systemPermissionCRUD)) {
				return context.proceed();
			} else {
				lancarPermissaoNegadaException(usuarioLogado, systemPermissionCRUD);
			}
		} else if (checarPermissao(usuarioLogado, fullMethodName)) {
			return context.proceed();
		} else {
			lancarPermissaoNegadaException(usuarioLogado, fullMethodName);
		}

		return null;
	}

	private Object verificarPermissoes(Class<?> classTarget, InvocationContext context)
			throws Exception, PermissaoNegadaException {

		UsuarioLogado usuarioLogado = Env.getUsuarioLogado();

		CheckBusinessAccess classLevel = null;
		CheckBusinessAccess methodLevel = null;
		CheckBusinessAccess annotation = null;

		if (classTarget.isAnnotationPresent(CheckBusinessAccess.class)) {
			classLevel = classTarget.getAnnotation(CheckBusinessAccess.class);
		}

		if (context.getMethod().isAnnotationPresent(CheckBusinessAccess.class)) {
			methodLevel = context.getMethod().getAnnotation(CheckBusinessAccess.class);
		}

		/* A prioridade é sempre a anotação no método */
		annotation = (methodLevel == null) ? classLevel : methodLevel;

		if (annotation != null && annotation.permission() != null && annotation.permission().length > 0) {
			String validarPermissao = null;
			boolean executar = false;
			for (String permissao : annotation.permission()) {

				if (OperationType.FIND.getDesc().equals(permissao.toLowerCase())) {
					validarPermissao = classTarget.getName() + "." + OperationType.FIND.getDesc();
				} else if (OperationType.SAVE.getDesc().equals(permissao.toLowerCase())) {
					validarPermissao = classTarget.getName() + "." + OperationType.SAVE.getDesc();
				} else if (OperationType.DELETE.getDesc().equals(permissao.toLowerCase())) {
					validarPermissao = classTarget.getName() + "." + OperationType.DELETE.getDesc();
				} else if (OperationType.MERGE.getDesc().equals(permissao.toLowerCase())) {
					validarPermissao = classTarget.getName() + "." + OperationType.MERGE.getDesc();
				} else if (OperationType.UPDATE.getDesc().equals(permissao.toLowerCase())) {
					validarPermissao = classTarget.getName() + "." + OperationType.UPDATE.getDesc();
				} else {
					validarPermissao = classTarget.getName() + "." + permissao;
				}

				if (executar = checarPermissao(usuarioLogado, validarPermissao)) {
					break;
				}
			}
			if (executar) {
				return context.proceed();
			} else {
				lancarPermissaoNegadaException(usuarioLogado, validarPermissao);
			}
		} else {
			String action = null;

			action = classTarget.getName() + "." + context.getMethod().getName();

			if (checarPermissao(usuarioLogado, action)) {
				return context.proceed();
			} else {
				lancarPermissaoNegadaException(usuarioLogado, action);
			}
		}
		return null;
	}

	private boolean checarPermissao(UsuarioLogado usuarioLogado, String fullMethodName) {

		if (usuarioLogado == null || isMethodNonRestricted(fullMethodName)
				|| genericService.isPermitido(usuarioLogado.getCodigo(), usuarioLogado.getAction(), fullMethodName)) {

			logAction(usuarioLogado);

			return true;

		}

		return false;
	}

	private void logAction(UsuarioLogado usuarioLogado) {
		if (usuarioLogado != null)
			em.unwrap(Session.class).doWork(new Work() {
				@Override
				public void execute(Connection conn) throws SQLException {
					// Obs: esta conn não precisa ser fechada explicitamente, pois é gerenciada pelo
					// Hibernate,
					// apesar do Warning que aparece na console
					StringBuilder sql = new StringBuilder();
					sql.append("update ").append(Constantes.TABELA_USUARIO).append(" set DATA_ULTIMO_ACESSO=? ")
							.append(" where IDEN_USUARIO=? ");
					try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
						ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
						ps.setLong(2, usuarioLogado.getCodigo());
						ps.executeUpdate();
					}
				}

			});
	}

	private void lancarPermissaoNegadaException(UsuarioLogado usuarioLogado, String fullMethodName)
			throws PermissaoNegadaException {
		String usuarioId = "Usuário[ id ] = " + usuarioLogado.getCodigo();
		String erro = "<div class='alert alert-danger'><strong>Ação: </strong>" + usuarioLogado.getAction() + "</div>"
				+ "<div class='alert alert-danger'><strong>Sem permissão</strong><p style='word-wrap: break-word;'>"
				+ fullMethodName + "</p></div>";

		if (FacesContext.getCurrentInstance() != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.put(PermissaoNegadaException.class.getName(), erro);
			if (fullMethodName.contains("br.gov.mt.mti.fiplangrf.service.relatorio.RelatorioGenericoService")) {
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("popupRelatorio", "true");
			}
		}
		LOGGER.error(usuarioId + " - " + erro);
		throw new PermissaoNegadaException(erro);
	}

	@PreDestroy
	public void myPreDestroyMethod(InvocationContext ctx) {
		try {
			ctx.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
