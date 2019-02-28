package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.common.util.CriptografiaUtil;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiroView;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroViewService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterRecursoFinanceiroViewBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirRecursoFinanceiroView", pattern = "/recursofinanceiroview/incluir", viewId = "/pages/tabelas/recursoFinanceiroView/manterRecursoFinanceiroView.jsf"),
		@URLMapping(id = "alterarRecursoFinanceiroView", pattern = "/recursofinanceiroview/alterar/#{id:manterRecursoFinanceiroViewBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiroView/manterRecursoFinanceiroView.jsf"),
		@URLMapping(id = "visualizarRecursoFinanceiroView", pattern = "/recursofinanceiroview/visualizar/#{id:manterRecursoFinanceiroViewBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiroView/manterRecursoFinanceiroView.jsf"),
		@URLMapping(id = "excluirRecursoFinanceiroView", pattern = "/recursofinanceiroview/excluir/#{id:manterRecursoFinanceiroViewBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiroView/manterRecursoFinanceiroView.jsf")})
public class ManterRecursoFinanceiroViewBean extends AbstractManterBean {

	private static final long serialVersionUID = 514933244195229478L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_RECURSOFINANCEIROVIEW_VIEW = "pretty:alterarRecursoFinanceiroView";

	public static final String VISUALIZAR_RECURSOFINANCEIROVIEW_VIEW = "pretty:visualizarRecursoFinanceiroView";

	public static final String EXCLUIR_RECURSOFINANCEIROVIEW_VIEW = "pretty:excluirRecursoFinanceiroView";

	public static final String PERMISSAO_INCLUIR_RECURSOFINANCEIROVIEW = "incluir.recursoFinanceiroView";

	public static final String PERMISSAO_ALTERAR_RECURSOFINANCEIROVIEW = "alterar.recursoFinanceiroView";

	public static final String PERMISSAO_EXCLUIR_RECURSOFINANCEIROVIEW = "excluir.recursoFinanceiroView";

	private Long id;

	private RecursoFinanceiroView recursoFinanceiroView;

	@Inject
	private RecursoFinanceiroViewService recursoFinanceiroViewService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		recursoFinanceiroView = new RecursoFinanceiroView();
	}

	@URLAction(mappingId = "incluirRecursoFinanceiroView", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_RECURSOFINANCEIROVIEW);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarRecursoFinanceiroView", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_RECURSOFINANCEIROVIEW);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarRecursoFinanceiroView", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirRecursoFinanceiroView", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_RECURSOFINANCEIROVIEW);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando RecursoFinanceiroView: {}", getId());
		recursoFinanceiroView = recursoFinanceiroViewService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = recursoFinanceiroView.getId() == null;
		LOGGER.debug("Persistindo RecursoFinanceiroView: {}", recursoFinanceiroView);
		recursoFinanceiroViewService.saveOrUpdate(recursoFinanceiroView);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo RecursoFinanceiroView: {}", getId());
		recursoFinanceiroViewService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}

	public RecursoFinanceiroView getRecursoFinanceiroView() {
		return recursoFinanceiroView;
	}

	public void setRecursoFinanceiroView(RecursoFinanceiroView recursoFinanceiroView) {
		this.recursoFinanceiroView = recursoFinanceiroView;
	}

}
