package br.gov.mt.mti.fiplangrf.web.bean.base;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.button.Button;
import org.primefaces.context.RequestContext;
import org.primefaces.expression.ComponentNotFoundException;

import br.gov.mt.cepromat.ceprofw.common.util.ExceptionUtil;
import br.gov.mt.cepromat.ceprofw.common.util.Mod11;
import br.gov.mt.cepromat.ceprofw.web.bean.ManterBaseBean;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.service.security.GenericService;
import br.gov.mt.mti.fiplangrf.service.security.exception.PermissaoNegadaException;

public abstract class AbstractManterBean extends ManterBaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -92290519314480129L;
	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	protected GenericService genericService;

	public void setAction(String action) {
		usuarioLogado.setAction(action);
	}

	public void setActionUpdate(String action) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		request.setAttribute("resetAction", Boolean.TRUE);

		if (encontrarComponente("reRunSetPermission") != null) {
			atualizarComponente("reRunSetPermission");
		}
		usuarioLogado.setAction(action);
	}

	public void validarHasFuncionalidade(String... funcionalidades) throws PermissaoNegadaException {
		if (funcionalidades == null || funcionalidades.length == 0) {
			throw new IllegalArgumentException(this.getClass().getName() + ".validarHasFuncionalidade ["
					+ "O parâmetro roles não deve ser vazio].");
		}
		if (!hasAtLeastOneFuncionalidade(funcionalidades)) {
			throw new PermissaoNegadaException("O usuário não possui a devida Role para esta funcionalidade.");
		}
	}

	public boolean hasPermissao(String permissao) {
		if (usuarioLogado != null) {
			return genericService.isPermitido(usuarioLogado.getCodigo(), usuarioLogado.getAction(), permissao);
		}
		return false;
	}

	public boolean hasFuncionalidade(String funcionalidade) {
		if (usuarioLogado != null) {
			return (genericService.isInFuncionalidade(usuarioLogado.getCodigo(), funcionalidade));
		}
		return false;
	}

	public boolean hasPapel(String... funcionalidades) {
		if (usuarioLogado != null) {
			return (genericService.isInPapel(usuarioLogado.getCodigo(), funcionalidades));
		}
		return false;
	}

	public boolean hasAtLeastOneFuncionalidade(String... funcionalidades) {
		for (String funcionalidade : funcionalidades) {
			if (hasFuncionalidade(funcionalidade)) {
				return true;
			}
		}
		return false;
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	protected void handleSQLIntegrityConstraintChildRecordFound(Exception e) {
		Throwable t = ExceptionUtil.getRootCause(e);
		if (t instanceof SQLIntegrityConstraintViolationException) {
			SQLIntegrityConstraintViolationException sqlCons = (SQLIntegrityConstraintViolationException) t;
			switch (sqlCons.getErrorCode()) {
			case 2292:
				addErrorMessage(
						"Não é possível efetuar a exclusão do registro, pois existem relacionamentos que impedem a exclusão.");
				break;
			case 1:
				addErrorMessage("Não é possível efetuar a inclusão/alteração do registro, restrição exclusiva.");
				break;
			default:
				addErrorMessage(e.getMessage());
				break;
			}
		} else {
			addErrorMessage(e.getMessage());
		}
	}

	protected UIComponent encontrarComponente(String id) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
	}

	protected void atualizarComponente(String id) {
		RequestContext.getCurrentInstance().update(id);
	}

	public void validarCadastroPessoa(String cpfCnpj) {

	}

	protected void closeDialog(String idComponent) {
		RequestContext.getCurrentInstance().execute("PF('" + idComponent + "').hide();");
	}

	protected void openDialog(String idComponent) {
		RequestContext.getCurrentInstance().execute("PF('" + idComponent + "').show();");
	}

	public void sendErrorDlg(String idMessage, String message) {
		FacesMessage msg = new FacesMessage(message);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(idMessage, msg);
	}

	/**
	 * M�todo que abre um dialog que exibe uma mensagem.
	 * 
	 * @param message  A mensagem a ser exibida
	 * @param severity O n�vel de gravidade da mensagem
	 * @param script   O script executado ao clicar no bot�o fechar do Dialog
	 * @param params   Par�metros utilizados em mensagens formatadas
	 */
	protected void showMainMsgDialog(String message, Severity severity, ButtonScript script, Object... params) {
		String textMessage = getMessageProvider().getValue(message, params);
		getFlash().setKeepMessages(true);
		openDialog(Constantes.MAIN_MSG_DIALOG);
		FacesContext.getCurrentInstance().addMessage(Constantes.MAIN_MSG_DIALOG + "Msg",
				new FacesMessage(severity, textMessage, textMessage));
		try {
			atualizarComponente(Constantes.MAIN_MSG_DIALOG + "_msg");
		} catch (ComponentNotFoundException e) {
			// Se o m�todo for executado ao carregar a p�gina, o componente n�o � encontrado
			// pois ainda n�o foi carregado e neste caso n�o � necess�rio atualiz�-lo.
			// atualizarComponente("@([id$=" + Constantes.MAIN_MSG_DIALOG + "_msg])");
		}
		setButtonScript(script);
	}

	protected void addGenericMessage(String idComponent, String message, Severity severity, Object... params) {
		String textMessage = getMessageProvider().getValue(message, params);
		FacesContext.getCurrentInstance().addMessage(idComponent, new FacesMessage(severity, textMessage, textMessage));
	}

	/**
	 * M�todo que abre um Dialog que exibe uma mensagem.
	 * 
	 * @param message A mensagem a ser exibida
	 */
	protected void showMainMsgDialog(String message) {
		showMainMsgDialog(message, null);
	}

	/**
	 * M�todo que abre um Dialog que exibe uma mensagem.
	 * 
	 * @param message A mensagem a ser exibida
	 * @param script  O script executado ao clicar no bot�o fechar do Dialog
	 */
	protected void showMainMsgDialog(String message, ButtonScript script) {
		showMainMsgDialog(message, FacesMessage.SEVERITY_INFO, script);
	}
	
	/**
	 * M�todo que abre um Dialog que exibe uma mensagem de erro.
	 * 
	 * @param message A mensagem a ser exibida
	 */
	protected void showMainErrorMsgDialog(String message) {
		showMainMsgDialog(message, null);
	}
	
	/**
	 * M�todo que abre um Dialog que exibe uma mensagem de erro.
	 * 
	 * @param message A mensagem a ser exibida
	 * @param script  O script executado ao clicar no bot�o fechar do Dialog
	 */
	protected void showMainErrorMsgDialog(String message, ButtonScript script) {
		showMainMsgDialog(message, FacesMessage.SEVERITY_ERROR, script);
	}
	
	/**
	 * Define o script a ser executado ao clicar no botão fechar do Dialog mainMsgDialog.
	 * @param script O script executado ao clicar no botão
	 */
	protected void setButtonScript(ButtonScript script) {
		setButtonScript(null, script);
	}


	/**
	 * Define o script a ser executado ao clicar no bot�o.
	 * 
	 * @param id     O id do componente
	 * @param script O script executado ao clicar no bot�o
	 */
	protected void setButtonScript(String id, ButtonScript script) {
		id = StringUtils.defaultIfEmpty(id, Constantes.MAIN_MSG_DIALOG + "_btn");
		String scriptString =  script != null ? script.toString() : "";
		Button btn = (Button) encontrarComponente(id);
		if (btn != null) {
			btn.setOnclick(scriptString);
			atualizarComponente(id);
		} else {
			RequestContext.getCurrentInstance().execute("$('#" + id + "').click(function(){" + scriptString + "})");
		}
	}

	protected void resetForm(String id) {
		id = StringUtils.defaultIfEmpty(id, Constantes.CRUD_FORM);
		RequestContext.getCurrentInstance().reset(id);
	}

	protected void resetForm() {
		resetForm(StringUtils.EMPTY);
	}

	/**
	 * Este m�todo dever� ser utilizado no atributo "validate" das tags JSF.
	 * 
	 * @param context
	 * @param component
	 * @param numeroDocumento
	 * @throws ValidatorException
	 */
	public void validateCpfCnpj(FacesContext context, UIComponent component, Object numeroDocumento)
			throws ValidatorException {
		String value = ((String) numeroDocumento).replaceAll("[^\\d.]", "");
		if (value.length() <= 11) {
			if (value instanceof String && ((String) value).length() > 0) {
				if (!Mod11.validateCPF((String) value)) {
					throw new br.gov.mt.cepromat.ceprofw.web.exception.ValidatorException(
							"br.gov.mt.cepromat.ceprofw.web.validator.CPFValidator.INVALID", context, component);
				}
			}
		} else {
			if (value != null && value instanceof String && ((String) value).length() > 0) {
				if (!Mod11.validateCNPJ((String) value)) {
					throw new br.gov.mt.cepromat.ceprofw.web.exception.ValidatorException(
							"br.gov.mt.cepromat.ceprofw.web.validator.CNPJValidator.INVALID", context, component);
				}
			}
		}
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public String getContextPath() {
		return getRequest().getContextPath();
	}

}
