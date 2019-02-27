package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.RevisaoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Revisao;
import br.gov.mt.mti.fiplangrf.service.tabelas.RevisaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarRevisaoBean")
@ViewScoped
@URLMapping(id = "pesquisarRevisao", pattern = "/revisao/pesquisar", viewId = "/pages/tabelas/revisao/pesquisarRevisao.jsf")
public class PesquisarRevisaoBean extends AbstractPesquisaBean<RevisaoCriteria> {

	private static final long serialVersionUID = 90274949701563415L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_REVISAO_VIEW = "pretty:pesquisarRevisao";

	public static final String PERMISSAO_PESQUISAR_REVISAO= "pesquisar.revisao";

	private LazyObjectDataModel<Revisao> resultadoPesquisa;

	@Inject
	private RevisaoService revisaoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_REVISAO);
		limpar();
	}

	public void limpar() {
		setCriteria(new RevisaoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Revisao: {}", getCriteria());
		
		getCriteria().setSortField("dataRevisao");
		getCriteria().setSortOrder("DESC");
		
		resultadoPesquisa = new LazyObjectDataModel<Revisao>(revisaoService, getCriteria());
	}

	public LazyObjectDataModel<Revisao> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Revisao> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
