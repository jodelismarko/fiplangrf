package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DetalheDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalheDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDetalheDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarDetalheDespesa", pattern = "/detalhedespesa/pesquisar", viewId = "/pages/tabelas/detalheDespesa/pesquisarDetalheDespesa.jsf")
public class PesquisarDetalheDespesaBean extends AbstractPesquisaBean<DetalheDespesaCriteria> {

	private static final long serialVersionUID = -799270979199167535L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DETALHEDESPESA_VIEW = "pretty:pesquisarDetalheDespesa";

	public static final String PERMISSAO_PESQUISAR_DETALHEDESPESA= "pesquisar.detalheDespesa";

	private LazyObjectDataModel<DetalheDespesa> resultadoPesquisa;

	@Inject
	private DetalheDespesaService detalheDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DETALHEDESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new DetalheDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de DetalheDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<DetalheDespesa>(detalheDespesaService, getCriteria());
	}

	public LazyObjectDataModel<DetalheDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<DetalheDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
