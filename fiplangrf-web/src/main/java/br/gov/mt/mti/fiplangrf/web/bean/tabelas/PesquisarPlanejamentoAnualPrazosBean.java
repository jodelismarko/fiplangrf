package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.PlanejamentoAnualPrazosCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;
import br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarPlanejamentoAnualPrazosBean")
@ViewScoped
@URLMapping(id = "pesquisarPlanejamentoAnualPrazos", pattern = "/planejamentoanualprazos/pesquisar", viewId = "/pages/tabelas/planejamentoAnualPrazos/pesquisarPlanejamentoAnualPrazos.jsf")
public class PesquisarPlanejamentoAnualPrazosBean extends AbstractPesquisaBean<PlanejamentoAnualPrazosCriteria> {

	private static final long serialVersionUID = 617344488526370915L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_PLANEJAMENTOANUALPRAZOS_VIEW = "pretty:pesquisarPlanejamentoAnualPrazos";

	public static final String PERMISSAO_PESQUISAR_PLANEJAMENTOANUALPRAZOS= "pesquisar.planejamentoAnualPrazos";

	private LazyObjectDataModel<PlanejamentoAnualPrazos> resultadoPesquisa;

	@Inject
	private PlanejamentoAnualPrazosService planejamentoAnualPrazosService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_PLANEJAMENTOANUALPRAZOS);
		limpar();
	}

	public void limpar() {
		setCriteria(new PlanejamentoAnualPrazosCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de PlanejamentoAnualPrazos: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<PlanejamentoAnualPrazos>(planejamentoAnualPrazosService, getCriteria());
	}

	public LazyObjectDataModel<PlanejamentoAnualPrazos> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<PlanejamentoAnualPrazos> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
