package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.FonteRecursoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;
import br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarFonteRecursoBean")
@ViewScoped
@URLMapping(id = "pesquisarFonteRecurso", pattern = "/fonterecurso/pesquisar", viewId = "/pages/tabelas/fonteRecurso/pesquisarFonteRecurso.jsf")
public class PesquisarFonteRecursoBean extends AbstractPesquisaBean<FonteRecursoCriteria> {

	private static final long serialVersionUID = 627187839335014365L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_FONTERECURSO_VIEW = "pretty:pesquisarFonteRecurso";

	public static final String PERMISSAO_PESQUISAR_FONTERECURSO= "pesquisar.fonteRecurso";

	private LazyObjectDataModel<FonteRecurso> resultadoPesquisa;

	@Inject
	private FonteRecursoService fonteRecursoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_FONTERECURSO);
		limpar();
	}

	public void limpar() {
		setCriteria(new FonteRecursoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de FonteRecurso: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<FonteRecurso>(fonteRecursoService, getCriteria());
	}

	public LazyObjectDataModel<FonteRecurso> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<FonteRecurso> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
