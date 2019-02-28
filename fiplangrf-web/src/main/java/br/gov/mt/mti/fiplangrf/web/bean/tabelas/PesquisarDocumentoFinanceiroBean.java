package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.DocumentoFinanceiroCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DocumentoFinanceiro;
import br.gov.mt.mti.fiplangrf.service.tabelas.DocumentoFinanceiroService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarDocumentoFinanceiroBean")
@ViewScoped
@URLMapping(id = "pesquisarDocumentoFinanceiro", pattern = "/documentofinanceiro/pesquisar", viewId = "/pages/tabelas/documentoFinanceiro/pesquisarDocumentoFinanceiro.jsf")
public class PesquisarDocumentoFinanceiroBean extends AbstractPesquisaBean<DocumentoFinanceiroCriteria> {

	private static final long serialVersionUID = -415908918330493671L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_DOCUMENTOFINANCEIRO_VIEW = "pretty:pesquisarDocumentoFinanceiro";

	public static final String PERMISSAO_PESQUISAR_DOCUMENTOFINANCEIRO= "pesquisar.documentoFinanceiro";

	private LazyObjectDataModel<DocumentoFinanceiro> resultadoPesquisa;

	@Inject
	private DocumentoFinanceiroService documentoFinanceiroService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_DOCUMENTOFINANCEIRO);
		limpar();
	}

	public void limpar() {
		setCriteria(new DocumentoFinanceiroCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de DocumentoFinanceiro: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<DocumentoFinanceiro>(documentoFinanceiroService, getCriteria());
	}

	public LazyObjectDataModel<DocumentoFinanceiro> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<DocumentoFinanceiro> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
