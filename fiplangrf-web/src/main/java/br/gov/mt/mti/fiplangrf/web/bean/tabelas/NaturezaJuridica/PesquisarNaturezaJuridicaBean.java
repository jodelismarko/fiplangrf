package br.gov.mt.mti.fiplangrf.web.bean.tabelas.NaturezaJuridica;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.NaturezaJuridicaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import br.gov.mt.mti.fiplangrf.service.tabelas.NaturezaJuridicaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarNaturezaJuridicaBean")
@ViewScoped
@URLMapping(id = "pesquisarNaturezaJuridica", pattern = "/naturezajuridica/pesquisar", viewId = "/pages/tabelas/naturezaJuridica/pesquisarNaturezaJuridica.jsf")
public class PesquisarNaturezaJuridicaBean extends AbstractPesquisaBean<NaturezaJuridicaCriteria> {

	private static final long serialVersionUID = -698712560317246440L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_NATUREZAJURIDICA_VIEW = "pretty:pesquisarNaturezaJuridica";

	public static final String PERMISSAO_PESQUISAR_NATUREZAJURIDICA= "pesquisar.naturezaJuridica";

	private LazyObjectDataModel<NaturezaJuridica> resultadoPesquisa;

	@Inject
	private NaturezaJuridicaService naturezaJuridicaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_NATUREZAJURIDICA);
		limpar();
	}

	public void limpar() {
		setCriteria(new NaturezaJuridicaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de NaturezaJuridica: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<NaturezaJuridica>(naturezaJuridicaService, getCriteria());
	}

	public LazyObjectDataModel<NaturezaJuridica> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<NaturezaJuridica> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
