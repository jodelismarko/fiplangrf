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
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoProvisaoDespesaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;

@Named("manterDetalhamentoProvisaoDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDetalhamentoProvisaoDespesa", pattern = "/detalhamentoprovisaodespesa/incluir", viewId = "/pages/tabelas/detalhamentoProvisaoDespesa/manterDetalhamentoProvisaoDespesa.jsf"),
		@URLMapping(id = "alterarDetalhamentoProvisaoDespesa", pattern = "/detalhamentoprovisaodespesa/alterar/#{id:manterDetalhamentoProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoProvisaoDespesa/manterDetalhamentoProvisaoDespesa.jsf"),
		@URLMapping(id = "visualizarDetalhamentoProvisaoDespesa", pattern = "/detalhamentoprovisaodespesa/visualizar/#{id:manterDetalhamentoProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoProvisaoDespesa/manterDetalhamentoProvisaoDespesa.jsf"),
		@URLMapping(id = "excluirDetalhamentoProvisaoDespesa", pattern = "/detalhamentoprovisaodespesa/excluir/#{id:manterDetalhamentoProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamentoProvisaoDespesa/manterDetalhamentoProvisaoDespesa.jsf")})
public class ManterDetalhamentoProvisaoDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = 283022680046172346L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DETALHAMENTOPROVISAODESPESA_VIEW = "pretty:alterarDetalhamentoProvisaoDespesa";

	public static final String VISUALIZAR_DETALHAMENTOPROVISAODESPESA_VIEW = "pretty:visualizarDetalhamentoProvisaoDespesa";

	public static final String EXCLUIR_DETALHAMENTOPROVISAODESPESA_VIEW = "pretty:excluirDetalhamentoProvisaoDespesa";

	public static final String PERMISSAO_INCLUIR_DETALHAMENTOPROVISAODESPESA = "incluir.detalhamentoProvisaoDespesa";

	public static final String PERMISSAO_ALTERAR_DETALHAMENTOPROVISAODESPESA = "alterar.detalhamentoProvisaoDespesa";

	public static final String PERMISSAO_EXCLUIR_DETALHAMENTOPROVISAODESPESA = "excluir.detalhamentoProvisaoDespesa";

	private Long id;

	private DetalhamentoProvisaoDespesa detalhamentoProvisaoDespesa;

	private List<Despesa> listaDespesaDetalProvisao;

	@Inject
	private DespesaService despesaService;

	@Inject
	private DetalhamentoProvisaoDespesaService detalhamentoProvisaoDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		detalhamentoProvisaoDespesa = new DetalhamentoProvisaoDespesa();
		LOGGER.debug("Carregando lista de Despesa");
		listaDespesaDetalProvisao = despesaService.findAll();
	}

	@URLAction(mappingId = "incluirDetalhamentoProvisaoDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DETALHAMENTOPROVISAODESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDetalhamentoProvisaoDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DETALHAMENTOPROVISAODESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDetalhamentoProvisaoDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDetalhamentoProvisaoDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DETALHAMENTOPROVISAODESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando DetalhamentoProvisaoDespesa: {}", getId());
		detalhamentoProvisaoDespesa = detalhamentoProvisaoDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo DetalhamentoProvisaoDespesa: {}", detalhamentoProvisaoDespesa);
		detalhamentoProvisaoDespesaService.saveOrUpdate(detalhamentoProvisaoDespesa);
		if (detalhamentoProvisaoDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo DetalhamentoProvisaoDespesa: {}", getId());
		detalhamentoProvisaoDespesaService.delete(getId());
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

	public DetalhamentoProvisaoDespesa getDetalhamentoProvisaoDespesa() {
		return detalhamentoProvisaoDespesa;
	}

	public void setDetalhamentoProvisaoDespesa(DetalhamentoProvisaoDespesa detalhamentoProvisaoDespesa) {
		this.detalhamentoProvisaoDespesa = detalhamentoProvisaoDespesa;
	}

	public List<Despesa> getListaDespesaDetalProvisao() {
		return listaDespesaDetalProvisao;
	}

	public void setListaDespesaDetalProvisao(List<Despesa> listaDespesaDetalProvisao) {
		this.listaDespesaDetalProvisao = listaDespesaDetalProvisao;
	}

}
