package br.gov.mt.mti.fiplangrf.web.bean.tabelas.SubGrupoPendencia;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.SubGrupoPendenciaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.SubGrupoPendencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.SubGrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarSubGrupoPendenciaBean")
@ViewScoped
@URLMapping(id = "pesquisarSubGrupoPendencia", pattern = "/subgrupopendencia/pesquisar", viewId = "/pages/tabelas/subGrupoPendencia/pesquisarSubGrupoPendencia.jsf")
public class PesquisarSubGrupoPendenciaBean extends AbstractPesquisaBean<SubGrupoPendenciaCriteria> {

	private static final long serialVersionUID = -303488739306122161L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_SUBGRUPOPENDENCIA_VIEW = "pretty:pesquisarSubGrupoPendencia";

	public static final String PERMISSAO_PESQUISAR_SUBGRUPOPENDENCIA= "pesquisar.subGrupoPendencia";

	private LazyObjectDataModel<SubGrupoPendencia> resultadoPesquisa;

	@Inject
	private SubGrupoPendenciaService subGrupoPendenciaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_SUBGRUPOPENDENCIA);
		limpar();
	}

	public void limpar() {
		setCriteria(new SubGrupoPendenciaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de SubGrupoPendencia: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<SubGrupoPendencia>(subGrupoPendenciaService, getCriteria());
	}

	public LazyObjectDataModel<SubGrupoPendencia> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<SubGrupoPendencia> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}
}
