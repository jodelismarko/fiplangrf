package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.HistoricoValorDetalhamentoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorDetalhamento;
import br.gov.mt.mti.fiplangrf.service.tabelas.HistoricoValorDetalhamentoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarHistoricoValorDetalhamentoBean")
@ViewScoped
@URLMapping(id = "pesquisarHistoricoValorDetalhamento", pattern = "/historicovalordetalhamento/pesquisar", viewId = "/pages/tabelas/historicoValorDetalhamento/pesquisarHistoricoValorDetalhamento.jsf")
public class PesquisarHistoricoValorDetalhamentoBean extends AbstractPesquisaBean<HistoricoValorDetalhamentoCriteria> {

	private static final long serialVersionUID = -359236193106883067L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_HISTORICOVALORDETALHAMENTO_VIEW = "pretty:pesquisarHistoricoValorDetalhamento";

	public static final String PERMISSAO_PESQUISAR_HISTORICOVALORDETALHAMENTO= "pesquisar.historicoValorDetalhamento";

	private LazyObjectDataModel<HistoricoValorDetalhamento> resultadoPesquisa;

	@Inject
	private HistoricoValorDetalhamentoService historicoValorDetalhamentoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_HISTORICOVALORDETALHAMENTO);
		limpar();
	}

	public void limpar() {
		setCriteria(new HistoricoValorDetalhamentoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de HistoricoValorDetalhamento: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<HistoricoValorDetalhamento>(historicoValorDetalhamentoService, getCriteria());
	}

	public LazyObjectDataModel<HistoricoValorDetalhamento> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<HistoricoValorDetalhamento> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
