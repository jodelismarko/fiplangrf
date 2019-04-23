package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DetalheProvisaoDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalheProvisaoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDetalheProvisaoDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarDetalheProvisaoDespesa", pattern = "/detalheprovisaodespesa/pesquisar", viewId = "/pages/tabelas/detalheProvisaoDespesa/pesquisarDetalheProvisaoDespesa.jsf")
public class PesquisarDetalheProvisaoDespesaBean extends AbstractPesquisaBean<DetalheProvisaoDespesaCriteria> {

	private static final long serialVersionUID = -658239743385134031L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DETALHEPROVISAODESPESA_VIEW = "pretty:pesquisarDetalheProvisaoDespesa";

	public static final String PERMISSAO_PESQUISAR_DETALHEPROVISAODESPESA= "pesquisar.detalheProvisaoDespesa";

	private LazyObjectDataModel<DetalheProvisaoDespesa> resultadoPesquisa;

	@Inject
	private DetalheProvisaoDespesaService detalheProvisaoDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DETALHEPROVISAODESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new DetalheProvisaoDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de DetalheProvisaoDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<DetalheProvisaoDespesa>(detalheProvisaoDespesaService, getCriteria());
	}

	public LazyObjectDataModel<DetalheProvisaoDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<DetalheProvisaoDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
