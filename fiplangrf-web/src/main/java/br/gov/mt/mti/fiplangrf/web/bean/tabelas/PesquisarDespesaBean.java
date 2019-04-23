package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarDespesa", pattern = "/despesa/pesquisar", viewId = "/pages/tabelas/despesa/pesquisarDespesa.jsf")
public class PesquisarDespesaBean extends AbstractPesquisaBean<DespesaCriteria> {

	private static final long serialVersionUID = 616474014917843679L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DESPESA_VIEW = "pretty:pesquisarDespesa";

	public static final String PERMISSAO_PESQUISAR_DESPESA= "pesquisar.despesa";

	private LazyObjectDataModel<Despesa> resultadoPesquisa;

	@Inject
	private DespesaService despesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new DespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Despesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<Despesa>(despesaService, getCriteria());
	}

	public LazyObjectDataModel<Despesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Despesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
