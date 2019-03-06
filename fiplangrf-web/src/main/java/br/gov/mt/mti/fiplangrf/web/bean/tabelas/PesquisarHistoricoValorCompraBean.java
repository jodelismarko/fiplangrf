package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.HistoricoValorCompraCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorCompra;
import br.gov.mt.mti.fiplangrf.service.tabelas.HistoricoValorCompraService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarHistoricoValorCompraBean")
@ViewScoped
@URLMapping(id = "pesquisarHistoricoValorCompra", pattern = "/historicovalorcompra/pesquisar", viewId = "/pages/tabelas/historicoValorCompra/pesquisarHistoricoValorCompra.jsf")
public class PesquisarHistoricoValorCompraBean extends AbstractPesquisaBean<HistoricoValorCompraCriteria> {

	private static final long serialVersionUID = 67219834662950826L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_HISTORICOVALORCOMPRA_VIEW = "pretty:pesquisarHistoricoValorCompra";

	public static final String PERMISSAO_PESQUISAR_HISTORICOVALORCOMPRA= "pesquisar.historicoValorCompra";

	private LazyObjectDataModel<HistoricoValorCompra> resultadoPesquisa;

	@Inject
	private HistoricoValorCompraService historicoValorCompraService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_HISTORICOVALORCOMPRA);
		limpar();
	}

	public void limpar() {
		setCriteria(new HistoricoValorCompraCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de HistoricoValorCompra: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<HistoricoValorCompra>(historicoValorCompraService, getCriteria());
	}

	public LazyObjectDataModel<HistoricoValorCompra> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<HistoricoValorCompra> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
