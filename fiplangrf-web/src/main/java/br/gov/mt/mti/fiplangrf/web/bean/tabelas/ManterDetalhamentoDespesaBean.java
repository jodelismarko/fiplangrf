package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterDetalhamentoDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDetalhamentoDespesa", pattern = "/detalhamentodespesa/incluir", viewId = "/pages/tabelas/detalhamentoDespesa/manterDetalhamentoDespesa.jsf"),
		@URLMapping(id = "alterarDetalhamentoDespesa", pattern = "/detalhamentodespesa/alterar/#{id:manterDetalhamentoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoDespesa/manterDetalhamentoDespesa.jsf"),
		@URLMapping(id = "visualizarDetalhamentoDespesa", pattern = "/detalhamentodespesa/visualizar/#{id:manterDetalhamentoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoDespesa/manterDetalhamentoDespesa.jsf"),
		@URLMapping(id = "excluirDetalhamentoDespesa", pattern = "/detalhamentodespesa/excluir/#{id:manterDetalhamentoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoDespesa/manterDetalhamentoDespesa.jsf")})
public class ManterDetalhamentoDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = 810751540215237061L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DETALHAMENTODESPESA_VIEW = "pretty:alterarDetalhamentoDespesa";

	public static final String VISUALIZAR_DETALHAMENTODESPESA_VIEW = "pretty:visualizarDetalhamentoDespesa";

	public static final String EXCLUIR_DETALHAMENTODESPESA_VIEW = "pretty:excluirDetalhamentoDespesa";

	public static final String PERMISSAO_INCLUIR_DETALHAMENTODESPESA = "incluir.detalhamentoDespesa";

	public static final String PERMISSAO_ALTERAR_DETALHAMENTODESPESA = "alterar.detalhamentoDespesa";

	public static final String PERMISSAO_EXCLUIR_DETALHAMENTODESPESA = "excluir.detalhamentoDespesa";

	private Long id;

	private DetalhamentoDespesa detalhamentoDespesa;

	@Inject
	private DetalhamentoDespesaService detalhamentoDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		detalhamentoDespesa = new DetalhamentoDespesa();
	}

	@URLAction(mappingId = "incluirDetalhamentoDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DETALHAMENTODESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDetalhamentoDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DETALHAMENTODESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDetalhamentoDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDetalhamentoDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DETALHAMENTODESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando DetalhamentoDespesa: {}", getId());
		detalhamentoDespesa = detalhamentoDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo DetalhamentoDespesa: {}", detalhamentoDespesa);
		detalhamentoDespesaService.saveOrUpdate(detalhamentoDespesa);
		if (detalhamentoDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo DetalhamentoDespesa: {}", getId());
		detalhamentoDespesaService.delete(getId());
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

	public DetalhamentoDespesa getDetalhamentoDespesa() {
		return detalhamentoDespesa;
	}

	public void setDetalhamentoDespesa(DetalhamentoDespesa detalhamentoDespesa) {
		this.detalhamentoDespesa = detalhamentoDespesa;
	}

}
