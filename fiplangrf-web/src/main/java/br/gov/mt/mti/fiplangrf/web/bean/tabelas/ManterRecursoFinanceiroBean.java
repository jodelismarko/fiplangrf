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
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;

@Named("manterRecursoFinanceiroBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirRecursoFinanceiro", pattern = "/recursofinanceiro/incluir", viewId = "/pages/tabelas/recursoFinanceiro/manterRecursoFinanceiro.jsf"),
		@URLMapping(id = "alterarRecursoFinanceiro", pattern = "/recursofinanceiro/alterar/#{id:manterRecursoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiro/manterRecursoFinanceiro.jsf"),
		@URLMapping(id = "visualizarRecursoFinanceiro", pattern = "/recursofinanceiro/visualizar/#{id:manterRecursoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiro/manterRecursoFinanceiro.jsf"),
		@URLMapping(id = "excluirRecursoFinanceiro", pattern = "/recursofinanceiro/excluir/#{id:manterRecursoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/recursoFinanceiro/manterRecursoFinanceiro.jsf")})
public class ManterRecursoFinanceiroBean extends AbstractManterBean {

	private static final long serialVersionUID = 948235512469182676L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_RECURSOFINANCEIRO_VIEW = "pretty:alterarRecursoFinanceiro";

	public static final String VISUALIZAR_RECURSOFINANCEIRO_VIEW = "pretty:visualizarRecursoFinanceiro";

	public static final String EXCLUIR_RECURSOFINANCEIRO_VIEW = "pretty:excluirRecursoFinanceiro";

	public static final String PERMISSAO_INCLUIR_RECURSOFINANCEIRO = "incluir.recursoFinanceiro";

	public static final String PERMISSAO_ALTERAR_RECURSOFINANCEIRO = "alterar.recursoFinanceiro";

	public static final String PERMISSAO_EXCLUIR_RECURSOFINANCEIRO = "excluir.recursoFinanceiro";

	private Long id;

	private RecursoFinanceiro recursoFinanceiro;

	@Inject
	private RecursoFinanceiroService recursoFinanceiroService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		recursoFinanceiro = new RecursoFinanceiro();
	}

	@URLAction(mappingId = "incluirRecursoFinanceiro", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_RECURSOFINANCEIRO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarRecursoFinanceiro", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_RECURSOFINANCEIRO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarRecursoFinanceiro", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirRecursoFinanceiro", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_RECURSOFINANCEIRO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando RecursoFinanceiro: {}", getId());
		recursoFinanceiro = recursoFinanceiroService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = recursoFinanceiro.getId() == null;
		LOGGER.debug("Persistindo RecursoFinanceiro: {}", recursoFinanceiro);
		recursoFinanceiroService.saveOrUpdate(recursoFinanceiro);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo RecursoFinanceiro: {}", getId());
		recursoFinanceiroService.delete(getId());
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

	public RecursoFinanceiro getRecursoFinanceiro() {
		return recursoFinanceiro;
	}

	public void setRecursoFinanceiro(RecursoFinanceiro recursoFinanceiro) {
		this.recursoFinanceiro = recursoFinanceiro;
	}

}
