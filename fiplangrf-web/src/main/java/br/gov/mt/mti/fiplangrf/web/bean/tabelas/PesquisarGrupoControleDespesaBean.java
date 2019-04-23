package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.GrupoControleDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarGrupoControleDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarGrupoControleDespesa", pattern = "/grupocontroledespesa/pesquisar", viewId = "/pages/tabelas/grupoControleDespesa/pesquisarGrupoControleDespesa.jsf")
public class PesquisarGrupoControleDespesaBean extends AbstractPesquisaBean<GrupoControleDespesaCriteria> {

	private static final long serialVersionUID = 408413162667105011L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_GRUPOCONTROLEDESPESA_VIEW = "pretty:pesquisarGrupoControleDespesa";

	public static final String PERMISSAO_PESQUISAR_GRUPOCONTROLEDESPESA= "pesquisar.grupoControleDespesa";

	private LazyObjectDataModel<GrupoControleDespesa> resultadoPesquisa;

	@Inject
	private GrupoControleDespesaService grupoControleDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_GRUPOCONTROLEDESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new GrupoControleDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de GrupoControleDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<GrupoControleDespesa>(grupoControleDespesaService, getCriteria());
	}

	public LazyObjectDataModel<GrupoControleDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<GrupoControleDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
