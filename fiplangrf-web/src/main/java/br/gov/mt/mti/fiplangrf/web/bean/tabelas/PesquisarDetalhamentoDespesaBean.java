package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DetalhamentoDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDetalhamentoDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarDetalhamentoDespesa", pattern = "/detalhamentodespesa/pesquisar", viewId = "/pages/tabelas/detalhamentoDespesa/pesquisarDetalhamentoDespesa.jsf")
public class PesquisarDetalhamentoDespesaBean extends AbstractPesquisaBean<DetalhamentoDespesaCriteria> {

	private static final long serialVersionUID = -351254391029511482L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DETALHAMENTODESPESA_VIEW = "pretty:pesquisarDetalhamentoDespesa";

	public static final String PERMISSAO_PESQUISAR_DETALHAMENTODESPESA= "pesquisar.detalhamentoDespesa";

	private LazyObjectDataModel<DetalhamentoDespesa> resultadoPesquisa;

	@Inject
	private DetalhamentoDespesaService detalhamentoDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DETALHAMENTODESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new DetalhamentoDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de DetalhamentoDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<DetalhamentoDespesa>(detalhamentoDespesaService, getCriteria());
	}

	public LazyObjectDataModel<DetalhamentoDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<DetalhamentoDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
