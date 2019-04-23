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
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalheDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterDetalheDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDetalheDespesa", pattern = "/detalhedespesa/incluir", viewId = "/pages/tabelas/detalheDespesa/manterDetalheDespesa.jsf"),
		@URLMapping(id = "alterarDetalheDespesa", pattern = "/detalhedespesa/alterar/#{id:manterDetalheDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheDespesa/manterDetalheDespesa.jsf"),
		@URLMapping(id = "visualizarDetalheDespesa", pattern = "/detalhedespesa/visualizar/#{id:manterDetalheDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheDespesa/manterDetalheDespesa.jsf"),
		@URLMapping(id = "excluirDetalheDespesa", pattern = "/detalhedespesa/excluir/#{id:manterDetalheDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheDespesa/manterDetalheDespesa.jsf")})
public class ManterDetalheDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = -507352256579158464L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DETALHEDESPESA_VIEW = "pretty:alterarDetalheDespesa";

	public static final String VISUALIZAR_DETALHEDESPESA_VIEW = "pretty:visualizarDetalheDespesa";

	public static final String EXCLUIR_DETALHEDESPESA_VIEW = "pretty:excluirDetalheDespesa";

	public static final String PERMISSAO_INCLUIR_DETALHEDESPESA = "incluir.detalheDespesa";

	public static final String PERMISSAO_ALTERAR_DETALHEDESPESA = "alterar.detalheDespesa";

	public static final String PERMISSAO_EXCLUIR_DETALHEDESPESA = "excluir.detalheDespesa";

	private Long id;

	private DetalheDespesa detalheDespesa;

	@Inject
	private DetalheDespesaService detalheDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		detalheDespesa = new DetalheDespesa();
	}

	@URLAction(mappingId = "incluirDetalheDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DETALHEDESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDetalheDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DETALHEDESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDetalheDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDetalheDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DETALHEDESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando DetalheDespesa: {}", getId());
		detalheDespesa = detalheDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo DetalheDespesa: {}", detalheDespesa);
		detalheDespesaService.saveOrUpdate(detalheDespesa);
		if (detalheDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo DetalheDespesa: {}", getId());
		detalheDespesaService.delete(getId());
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

	public DetalheDespesa getDetalheDespesa() {
		return detalheDespesa;
	}

	public void setDetalheDespesa(DetalheDespesa detalheDespesa) {
		this.detalheDespesa = detalheDespesa;
	}

}
