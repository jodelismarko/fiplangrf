package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DetalhamentoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDetalhamentoBean")
@ViewScoped
@URLMapping(id = "pesquisarDetalhamento", pattern = "/detalhamento/pesquisar", viewId = "/pages/tabelas/detalhamento/pesquisarDetalhamento.jsf")
public class PesquisarDetalhamentoBean extends AbstractPesquisaBean<DetalhamentoCriteria> {

	private static final long serialVersionUID = -475638985973393066L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DETALHAMENTO_VIEW = "pretty:pesquisarDetalhamento";

	public static final String PERMISSAO_PESQUISAR_DETALHAMENTO= "pesquisar.detalhamento";

	private LazyObjectDataModel<Detalhamento> resultadoPesquisa;

	@Inject
	private DetalhamentoService detalhamentoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DETALHAMENTO);
		limpar();
	}

	public void limpar() {
		setCriteria(new DetalhamentoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Detalhamento: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<Detalhamento>(detalhamentoService, getCriteria());
	}

	public LazyObjectDataModel<Detalhamento> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Detalhamento> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
