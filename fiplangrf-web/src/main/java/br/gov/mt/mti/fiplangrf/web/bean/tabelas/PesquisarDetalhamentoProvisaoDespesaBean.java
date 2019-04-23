package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DetalhamentoProvisaoDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDetalhamentoProvisaoDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarDetalhamentoProvisaoDespesa", pattern = "/detalhamentoprovisaodespesa/pesquisar", viewId = "/pages/tabelas/detalhamentoProvisaoDespesa/pesquisarDetalhamentoProvisaoDespesa.jsf")
public class PesquisarDetalhamentoProvisaoDespesaBean extends AbstractPesquisaBean<DetalhamentoProvisaoDespesaCriteria> {

	private static final long serialVersionUID = 860514100408974522L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DETALHAMENTOPROVISAODESPESA_VIEW = "pretty:pesquisarDetalhamentoProvisaoDespesa";

	public static final String PERMISSAO_PESQUISAR_DETALHAMENTOPROVISAODESPESA= "pesquisar.detalhamentoProvisaoDespesa";

	private LazyObjectDataModel<DetalhamentoProvisaoDespesa> resultadoPesquisa;

	@Inject
	private DetalhamentoProvisaoDespesaService detalhamentoProvisaoDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DETALHAMENTOPROVISAODESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new DetalhamentoProvisaoDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de DetalhamentoProvisaoDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<DetalhamentoProvisaoDespesa>(detalhamentoProvisaoDespesaService, getCriteria());
	}

	public LazyObjectDataModel<DetalhamentoProvisaoDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<DetalhamentoProvisaoDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
