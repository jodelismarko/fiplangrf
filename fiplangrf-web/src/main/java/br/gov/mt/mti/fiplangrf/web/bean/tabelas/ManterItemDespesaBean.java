package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.common.util.CriptografiaUtil;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.model.tabelas.ItemDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.ItemDespesaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;

@Named("manterItemDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirItemDespesa", pattern = "/itemdespesa/incluir", viewId = "/pages/tabelas/itemDespesa/manterItemDespesa.jsf"),
		@URLMapping(id = "alterarItemDespesa", pattern = "/itemdespesa/alterar/#{id:manterItemDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/itemDespesa/manterItemDespesa.jsf"),
		@URLMapping(id = "visualizarItemDespesa", pattern = "/itemdespesa/visualizar/#{id:manterItemDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/itemDespesa/manterItemDespesa.jsf"),
		@URLMapping(id = "excluirItemDespesa", pattern = "/itemdespesa/excluir/#{id:manterItemDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/itemDespesa/manterItemDespesa.jsf")})
public class ManterItemDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = -47568819499494021L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_ITEMDESPESA_VIEW = "pretty:alterarItemDespesa";

	public static final String VISUALIZAR_ITEMDESPESA_VIEW = "pretty:visualizarItemDespesa";

	public static final String EXCLUIR_ITEMDESPESA_VIEW = "pretty:excluirItemDespesa";

	public static final String PERMISSAO_INCLUIR_ITEMDESPESA = "incluir.itemDespesa";

	public static final String PERMISSAO_ALTERAR_ITEMDESPESA = "alterar.itemDespesa";

	public static final String PERMISSAO_EXCLUIR_ITEMDESPESA = "excluir.itemDespesa";

	private Long id;

	private ItemDespesa itemDespesa;

	private List<GrupoControleDespesa> listaGrupoControleDespesa;

	@Inject
	private GrupoControleDespesaService grupoControleDespesaService;

	@Inject
	private ItemDespesaService itemDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		itemDespesa = new ItemDespesa();
		LOGGER.debug("Carregando lista de GrupoControleDespesa");
		listaGrupoControleDespesa = grupoControleDespesaService.findAll();
	}

	@URLAction(mappingId = "incluirItemDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_ITEMDESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarItemDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_ITEMDESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarItemDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirItemDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_ITEMDESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando ItemDespesa: {}", getId());
		itemDespesa = itemDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo ItemDespesa: {}", itemDespesa);
		itemDespesaService.saveOrUpdate(itemDespesa);
		if (itemDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo ItemDespesa: {}", getId());
		itemDespesaService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}

	public ItemDespesa getItemDespesa() {
		return itemDespesa;
	}

	public void setItemDespesa(ItemDespesa itemDespesa) {
		this.itemDespesa = itemDespesa;
	}

	public List<GrupoControleDespesa> getListaGrupoControleDespesa() {
		return listaGrupoControleDespesa;
	}

	public void setListaGrupoControleDespesa(List<GrupoControleDespesa> listaGrupoControleDespesa) {
		this.listaGrupoControleDespesa = listaGrupoControleDespesa;
	}

}
