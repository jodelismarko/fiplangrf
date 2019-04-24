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
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoControleDespesaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;

@Named("manterGrupoControleDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirGrupoControleDespesa", pattern = "/grupocontroledespesa/incluir", viewId = "/pages/tabelas/grupoControleDespesa/manterGrupoControleDespesa.jsf"),
		@URLMapping(id = "alterarGrupoControleDespesa", pattern = "/grupocontroledespesa/alterar/#{id:manterGrupoControleDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoControleDespesa/manterGrupoControleDespesa.jsf"),
		@URLMapping(id = "visualizarGrupoControleDespesa", pattern = "/grupocontroledespesa/visualizar/#{id:manterGrupoControleDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoControleDespesa/manterGrupoControleDespesa.jsf"),
		@URLMapping(id = "excluirGrupoControleDespesa", pattern = "/grupocontroledespesa/excluir/#{id:manterGrupoControleDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoControleDespesa/manterGrupoControleDespesa.jsf")})
public class ManterGrupoControleDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = 472440772280697697L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_GRUPOCONTROLEDESPESA_VIEW = "pretty:alterarGrupoControleDespesa";

	public static final String VISUALIZAR_GRUPOCONTROLEDESPESA_VIEW = "pretty:visualizarGrupoControleDespesa";

	public static final String EXCLUIR_GRUPOCONTROLEDESPESA_VIEW = "pretty:excluirGrupoControleDespesa";

	public static final String PERMISSAO_INCLUIR_GRUPOCONTROLEDESPESA = "incluir.grupoControleDespesa";

	public static final String PERMISSAO_ALTERAR_GRUPOCONTROLEDESPESA = "alterar.grupoControleDespesa";

	public static final String PERMISSAO_EXCLUIR_GRUPOCONTROLEDESPESA = "excluir.grupoControleDespesa";

	private Long id;

	private GrupoControleDespesa grupoControleDespesa;

	private List<Despesa> listaDespesa;

	@Inject
	private DespesaService despesaService;

	@Inject
	private GrupoControleDespesaService grupoControleDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		grupoControleDespesa = new GrupoControleDespesa();
		LOGGER.debug("Carregando lista de Despesa");
		listaDespesa = despesaService.findAll();
	}

	@URLAction(mappingId = "incluirGrupoControleDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_GRUPOCONTROLEDESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarGrupoControleDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_GRUPOCONTROLEDESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarGrupoControleDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirGrupoControleDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_GRUPOCONTROLEDESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando GrupoControleDespesa: {}", getId());
		grupoControleDespesa = grupoControleDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo GrupoControleDespesa: {}", grupoControleDespesa);
		grupoControleDespesaService.saveOrUpdate(grupoControleDespesa);
		if (grupoControleDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo GrupoControleDespesa: {}", getId());
		grupoControleDespesaService.delete(getId());
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

	public GrupoControleDespesa getGrupoControleDespesa() {
		return grupoControleDespesa;
	}

	public void setGrupoControleDespesa(GrupoControleDespesa grupoControleDespesa) {
		this.grupoControleDespesa = grupoControleDespesa;
	}

	public List<Despesa> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<Despesa> listaDespesa) {
		this.listaDespesa = listaDespesa;
	}

}
