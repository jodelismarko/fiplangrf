package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.RecursoFinanceiroCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarRecursoFinanceiroBean")
@ViewScoped
@URLMapping(id = "pesquisarRecursoFinanceiro", pattern = "/recursofinanceiro/pesquisar", viewId = "/pages/tabelas/recursoFinanceiro/pesquisarRecursoFinanceiro.jsf")
public class PesquisarRecursoFinanceiroBean extends AbstractPesquisaBean<RecursoFinanceiroCriteria> {

	private static final long serialVersionUID = -785701979540911205L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_RECURSOFINANCEIRO_VIEW = "pretty:pesquisarRecursoFinanceiro";

	public static final String PERMISSAO_PESQUISAR_RECURSOFINANCEIRO= "pesquisar.recursoFinanceiro";

	private LazyObjectDataModel<RecursoFinanceiro> resultadoPesquisa;

	@Inject
	private RecursoFinanceiroService recursoFinanceiroService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_RECURSOFINANCEIRO);
		limpar();
	}

	public void limpar() {
		setCriteria(new RecursoFinanceiroCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de RecursoFinanceiro: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<RecursoFinanceiro>(recursoFinanceiroService, getCriteria());
	}

	public LazyObjectDataModel<RecursoFinanceiro> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<RecursoFinanceiro> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
