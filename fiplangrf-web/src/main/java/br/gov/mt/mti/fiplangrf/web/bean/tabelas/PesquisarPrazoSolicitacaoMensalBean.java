package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.PrazoSolicitacaoMensalCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.PrazoSolicitacaoMensal;
import br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarPrazoSolicitacaoMensalBean")
@ViewScoped
@URLMapping(id = "pesquisarPrazoSolicitacaoMensal", pattern = "/prazosolicitacaomensal/pesquisar", viewId = "/pages/tabelas/prazoSolicitacaoMensal/pesquisarPrazoSolicitacaoMensal.jsf")
public class PesquisarPrazoSolicitacaoMensalBean extends AbstractPesquisaBean<PrazoSolicitacaoMensalCriteria> {

	private static final long serialVersionUID = 831265798617632836L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_PRAZOSOLICITACAOMENSAL_VIEW = "pretty:pesquisarPrazoSolicitacaoMensal";

	public static final String PERMISSAO_PESQUISAR_PRAZOSOLICITACAOMENSAL= "pesquisar.prazoSolicitacaoMensal";

	private LazyObjectDataModel<PrazoSolicitacaoMensal> resultadoPesquisa;

	@Inject
	private PrazoSolicitacaoMensalService prazoSolicitacaoMensalService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_PRAZOSOLICITACAOMENSAL);
		limpar();
	}

	public void limpar() {
		setCriteria(new PrazoSolicitacaoMensalCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de PrazoSolicitacaoMensal: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<PrazoSolicitacaoMensal>(prazoSolicitacaoMensalService, getCriteria());
	}

	public LazyObjectDataModel<PrazoSolicitacaoMensal> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<PrazoSolicitacaoMensal> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
