package br.gov.mt.mti.fiplangrf.service.relatorio;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.plexus.util.StringUtils;
import org.hibernate.Session;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.Expression;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterField;
import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.cepromat.ceprofw.core.dominio.DominioFormatoSaidaRelatorio;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.cepromat.ceprofw.core.service.annotation.OperationType;
import br.gov.mt.cepromat.ceprofw.core.service.annotation.SystemBusinessPermission;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.util.report.ReportExporter;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RelatorioGenericoService {

	@PersistenceContext
	protected EntityManager em;

	private boolean certificado;

	private final String USUARIO_LOGADO = "USUARIO_LOGADO";
	private final String LOGO = "LOGO";
	private final String BRASAO = "BRASAO";
	private final String AMBIENTE = "AMBIENTE";
	private final String CABECALHO = "CABECALHO";
	private final String RODAPE = "RODAPE";
	private final String TEXTO_RODAPE = "TEXTO_RODAPE";
	private final String FILTROS = "FILTROS";
	private final String ORDENACAO = "ORDENACAO";

	private final String RODAPE_FILE = "/relatorios/rodape.jasper";
	private final String CABECALHO_FILE = "/relatorios/cabecalho.jasper";

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	protected RelatorioGenericoService() {

	}

	private Map<String, Object> parametros = new HashMap<String, Object>();

	@SystemBusinessPermission(operation = OperationType.FIND)
	public void executarRelatorio(String reportPath, DominioFormatoSaidaRelatorio formatoSaidaRelatorio, String nomeRelatorio) throws BusinessException {
		configurarParametros();

		gerarRelatorio(reportPath, formatoSaidaRelatorio, nomeRelatorio);
	}

	@SystemBusinessPermission(operation = OperationType.FIND)
	public void executarRelatorio(String reportPath, DynamicSearchCriteria<?> criteria) throws BusinessException {

		executarRelatorio(reportPath, criteria, criteria.getType().getSimpleName());
	}

	@SystemBusinessPermission(operation = OperationType.FIND)
	public void executarRelatorio(String reportPath, DynamicSearchCriteria<?> criteria, String nomeRelatorio) throws BusinessException {

		StringBuilder filtros = new StringBuilder();
		for (final FilterField filterField : criteria.getModel().getSelectedFields()) {

			if (filterField.getValue() == null) {
				throw new BusinessException("Por favor informe o parâmetro do filtro!");
			}

			if ((!filterField.getValue().equals("")) || (filterField.isMultiselect() && filterField.getItemsValues() != null)) {

				if (filterField.getType().equals(FieldType.DATE) && filterField.getValue() != null) {
					
					if (filterField.getExpression().equals(Expression.BETWEEN)) {
						filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " " + sdf.format((Date) filterField.getValue()) + " e " + sdf.format((Date) filterField.getValue2()) + "\n");
					} else {
						filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " " + sdf.format((Date) filterField.getValue()) + "\n");
					}
				}

				else if (filterField.getType().equals(FieldType.LIST)) {

					for (SelectItem item : filterField.getSelectItems()) {
						if (item.getValue().toString().equals(filterField.getValue().toString())) {
							filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " \"" + item.getLabel() + "\"\n");
						}
					}

				} else if (filterField.getType().equals(FieldType.ENUM)) {

					for (SelectItem item : filterField.getSelectItems()) {
						if (item.getValue().toString().equals(filterField.getValue().toString())) {
							filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " \"" + item.getLabel() + "\"\n");
						}
					}

				} else if(filterField.getType().equals(FieldType.LUPA)) {
					int index = filterField.getLabelProperty().indexOf(".") + 1;
					try {
						filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " "							
								+ BeanUtils.getProperty(filterField.getValue(), filterField.getLabelProperty().substring(index)) + "\n");
					} catch (Exception e) { 
						e.printStackTrace();
					} 
					
				} else {

					filtros.append(filterField.getDescription() + " " + filterField.getExpression().getDescription() + " " + filterField.getValue() + "\n");
				}
				parametros.put(filterField.getField().toUpperCase(), expressionResolver(filterField));				

			}
		}

		for (final FilterField filterField : criteria.getModel().getAllFields()) {

			if (parametros.get(filterField.getField().toUpperCase()) == null) {

				parametros.put(filterField.getField().toUpperCase(), "is not null");
			}
		}

		// remove a quebra de linha apos o ultimo filtro
		if (filtros.length() > 0) {
			filtros.deleteCharAt(filtros.length() - 1);
		}
		
		if(StringUtils.isNotBlank(criteria.getModel().getOrdenacaoSelecionada())) {
			parametros.put(ORDENACAO, criteria.getModel().getOrdenacaoSelecionada());
		}
		
		parametros.put(FILTROS, filtros.toString());
		configurarParametros();

		gerarRelatorio(reportPath, criteria.getFormatoSaida(), nomeRelatorio);

	}

	private void gerarRelatorio(String reportPath, DominioFormatoSaidaRelatorio formatoSaidaRelatorio, String nomeRelatorio) throws BusinessException {
		ReportExporter executor = new ReportExporter(reportPath, parametros, formatoSaidaRelatorio);

		getSession().doWork(executor);

		if (!executor.isRelatorioGerado()) {
			throw new BusinessException("A execução do relatório não retornou dados.");
		}
		ByteArrayOutputStream relatorio = executor.getRelatorio();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		response.setHeader("content-length", String.valueOf(relatorio.size()));
		if (formatoSaidaRelatorio.equals(DominioFormatoSaidaRelatorio.PDF)) {
			response.setHeader("content-disposition", "inline; filename=\"relatorio " + nomeRelatorio + ".pdf\"");
			response.setContentType("application/pdf");
		} else if (formatoSaidaRelatorio.equals(DominioFormatoSaidaRelatorio.XLS)) {
			response.setContentType("application/excel");
			response.setHeader("content-disposition", "attachment; filename=\"relatorio " + nomeRelatorio + ".xlsx\"");
		} else if (formatoSaidaRelatorio.equals(DominioFormatoSaidaRelatorio.DOC)) {
			response.setContentType("application/application/msword");
			response.setHeader("content-disposition", "attachment; filename=\"relatorio " + nomeRelatorio + ".doc\"");
		}
		try {
			relatorio.writeTo(response.getOutputStream());
			relatorio.flush();
			relatorio.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fc.responseComplete();
	}

	public void addParam(String key, Object value) {
		parametros.put(key, value);
	}

	private void configurarParametros() {
		
		parametros.put(USUARIO_LOGADO, Env.getUsuarioLogado().getCodigo() + " - " + Env.getUsuarioLogado().getNome());

		try {
			BufferedImage logo = getLogoInstituicao();
			parametros.put(LOGO, logo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BufferedImage brasao = getBrasaoEstado();
			parametros.put(BRASAO, brasao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		parametros.put(CABECALHO, FacesContext.getCurrentInstance().getExternalContext().getRealPath(CABECALHO_FILE));
		parametros.put(RODAPE, FacesContext.getCurrentInstance().getExternalContext().getRealPath(RODAPE_FILE));
		parametros.put(TEXTO_RODAPE, "RODAPÉ DO RELATÓRIO");

		parametros.put(AMBIENTE, getImageAmbiente());
	}

	private BufferedImage getLogoInstituicao() {
		try {
			File f = new File("/relatorios/imagens/logoindea.png");
			if (f.exists()) {
				BufferedImage logo = ImageIO.read(f);
				return logo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private BufferedImage getBrasaoEstado() {
		try {
			File f = new File("/relatorios/imagens/brasao_estado.png");
			if (f.exists()) {
				BufferedImage logo = ImageIO.read(f);
				return logo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private BufferedImage getImageAmbiente() {
		String imageLogoPath = null;
		if ("des".equals(EnvUtil.getAmbiente())) {
			imageLogoPath = "/relatorios/imagens/desenvolvimento.png";

		} else if ("int".equals(EnvUtil.getAmbiente())) {
			imageLogoPath = "/relatorios/imagens/integracao.png";
		} else if ("hom".equals(EnvUtil.getAmbiente())) {
			imageLogoPath = "/relatorios/imagens/homologacao.png";

		}

		String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(imageLogoPath);

		try {
			File f = new File(logoPath);
			if (f.exists()) {
				BufferedImage logo = ImageIO.read(f);
				return logo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String expressionResolver(FilterField filterField) {
		Expression expression = filterField.getExpression();

		Object value1 = filterField.getValue();
		Object value2 = filterField.getValue2();

		StringBuilder q = new StringBuilder("");
		if (expression.equals(Expression.EQUAL)) {
			setValor("=", q, filterField);
		} else if (expression.equals(Expression.DIFFERENT) || expression.equals(Expression.NENHUM)) {
			setValor("<>", q, filterField);
		} else if (expression.equals(Expression.CONTAINS)) {
			q.append(" like '%" + ((String) value1).toUpperCase() + "%' ");
		} else if (expression.equals(Expression.BEGIN_WITH)) {
			q.append(" like '" + ((String) value1).toUpperCase() + "%' ");
		} else if (expression.equals(Expression.ENDS_WITH)) {
			q.append(" like '%" + ((String) value1).toUpperCase() + "' ");
		} else if (expression.equals(Expression.LESS_THAN)) {
			setValor(" <", q, filterField);
		} else if (expression.equals(Expression.GREATER_THAN)) {
			setValor(" > ", q, filterField);
		} else if (expression.equals(Expression.LESS_OR_EQUAL_THAN)) {
			setValor(" <= ", q, filterField);
		} else if (expression.equals(Expression.GREATER_OR_EQUAL_THAN)) {
			setValor(" >= ", q, filterField);
		} else if (expression.equals(Expression.BETWEEN)) {

			if (filterField.getType().equals(FieldType.DATE) && filterField.getValue() != null) {
				q.append(" between  TO_DATE('" + sdf.format((Date) filterField.getValue()) + "','dd/MM/yyyy') and  TO_DATE('" + sdf.format((Date) filterField.getValue2()) + "','dd/MM/yyyy')");
			} else {
				q.append(" between " + value1 + " and " + value2);
			}
		}

		if (q.toString().equals("")) {
			return " <> '' or (1=1) ";
		} else {
			return q.toString();
		}

	}

	public void setValor(String expr, StringBuilder q, FilterField filterField) {
		q.append(" " + expr + " ");
		if(filterField.getType().equals(FieldType.LUPA)) {
			try {
				q.append(BeanUtils.getProperty(filterField, "value.id"));
			} catch (Exception e) {				
				e.printStackTrace();
			} 
		} else if (filterField.getType().equals(FieldType.DATE) && filterField.getValue() != null) {
			q.append(" TO_DATE('" + sdf.format((Date) filterField.getValue()) + "','dd/MM/yyyy')");
		} else if (filterField.getValue() instanceof String) {
			q.append("'" + ((String) filterField.getValue()).toUpperCase() + "' ");
		} else {
			q.append(filterField.getValue());
		}
	}

	protected Session getSession() {
		return em.unwrap(Session.class);
	}

	@SystemBusinessPermission(operation = OperationType.FIND)
	public void resetarParametros() {
		parametros = new HashMap<String, Object>();
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

}
