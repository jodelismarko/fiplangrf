package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.ItemDespesaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.ItemDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarItemDespesaBean")
@ViewScoped
@URLMapping(id = "pesquisarItemDespesa", pattern = "/itemdespesa/pesquisar", viewId = "/pages/tabelas/itemDespesa/pesquisarItemDespesa.jsf")
public class PesquisarItemDespesaBean extends AbstractPesquisaBean<ItemDespesaCriteria> {

	private static final long serialVersionUID = -401833200620638796L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_ITEMDESPESA_VIEW = "pretty:pesquisarItemDespesa";

	public static final String PERMISSAO_PESQUISAR_ITEMDESPESA= "pesquisar.itemDespesa";

	private LazyObjectDataModel<ItemDespesa> resultadoPesquisa;

	@Inject
	private ItemDespesaService itemDespesaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_ITEMDESPESA);
		limpar();
	}

	public void limpar() {
		setCriteria(new ItemDespesaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de ItemDespesa: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<ItemDespesa>(itemDespesaService, getCriteria());
	}

	public LazyObjectDataModel<ItemDespesa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<ItemDespesa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
