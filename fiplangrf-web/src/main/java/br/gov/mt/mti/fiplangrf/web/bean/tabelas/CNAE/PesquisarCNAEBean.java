package br.gov.mt.mti.fiplangrf.web.bean.tabelas.CNAE;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.CNAECriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAE;
import br.gov.mt.mti.fiplangrf.service.tabelas.CNAEService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarCNAEBean")
@ViewScoped
@URLMapping(id = "pesquisarCNAE", pattern = "/cnae/pesquisar", viewId = "/pages/tabelas/cnae/pesquisarCNAE.jsf")
public class PesquisarCNAEBean extends AbstractPesquisaBean<CNAECriteria> {

	private static final long serialVersionUID = 166754680137429747L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_CNAE_VIEW = "pretty:pesquisarCNAE";

	public static final String PERMISSAO_PESQUISAR_CNAE= "pesquisar.cnae";

	private LazyObjectDataModel<CNAE> resultadoPesquisa;

	@Inject
	private CNAEService cnaeService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_CNAE);
		limpar();
	}

	public void limpar() {
		setCriteria(new CNAECriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de CNAE: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<CNAE>(cnaeService, getCriteria());
	}

	public LazyObjectDataModel<CNAE> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<CNAE> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
