package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.RecursoFinanceiroViewCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiroView;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroViewService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarRecursoFinanceiroViewBean")
@ViewScoped
@URLMapping(id = "pesquisarRecursoFinanceiroView", pattern = "/recursofinanceiroview/pesquisar", viewId = "/pages/tabelas/recursoFinanceiroView/pesquisarRecursoFinanceiroView.jsf")
public class PesquisarRecursoFinanceiroViewBean extends AbstractPesquisaBean<RecursoFinanceiroViewCriteria> {

	private static final long serialVersionUID = -254754681153056900L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_RECURSOFINANCEIROVIEW_VIEW = "pretty:pesquisarRecursoFinanceiroView";

	public static final String PERMISSAO_PESQUISAR_RECURSOFINANCEIROVIEW= "pesquisar.recursoFinanceiroView";

	private LazyObjectDataModel<RecursoFinanceiroView> resultadoPesquisa;

	@Inject
	private RecursoFinanceiroViewService recursoFinanceiroViewService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_RECURSOFINANCEIROVIEW);
		limpar();
	}

	public void limpar() {
		setCriteria(new RecursoFinanceiroViewCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de RecursoFinanceiroView: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<RecursoFinanceiroView>(recursoFinanceiroViewService, getCriteria());
	}

	public LazyObjectDataModel<RecursoFinanceiroView> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<RecursoFinanceiroView> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
