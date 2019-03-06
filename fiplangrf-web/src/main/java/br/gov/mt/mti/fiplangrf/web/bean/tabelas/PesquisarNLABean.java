package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.NLACriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.NLA;
import br.gov.mt.mti.fiplangrf.service.tabelas.NLAService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarNLABean")
@ViewScoped
@URLMapping(id = "pesquisarNLA", pattern = "/nla/pesquisar", viewId = "/pages/tabelas/nLA/pesquisarNLA.jsf")
public class PesquisarNLABean extends AbstractPesquisaBean<NLACriteria> {

	private static final long serialVersionUID = 222775424379399959L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_NLA_VIEW = "pretty:pesquisarNLA";

	public static final String PERMISSAO_PESQUISAR_NLA= "pesquisar.nLA";

	private LazyObjectDataModel<NLA> resultadoPesquisa;

	@Inject
	private NLAService nLAService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_NLA);
		limpar();
	}

	public void limpar() {
		setCriteria(new NLACriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de NLA: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<NLA>(nLAService, getCriteria());
	}

	public LazyObjectDataModel<NLA> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<NLA> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
