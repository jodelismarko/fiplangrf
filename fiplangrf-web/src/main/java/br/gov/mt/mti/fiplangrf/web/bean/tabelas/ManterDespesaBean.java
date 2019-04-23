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
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DespesaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalheProvisaoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheProvisaoDespesa;

@Named("manterDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDespesa", pattern = "/despesa/incluir", viewId = "/pages/tabelas/despesa/manterDespesa.jsf"),
		@URLMapping(id = "alterarDespesa", pattern = "/despesa/alterar/#{id:manterDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/despesa/manterDespesa.jsf"),
		@URLMapping(id = "visualizarDespesa", pattern = "/despesa/visualizar/#{id:manterDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/despesa/manterDespesa.jsf"),
		@URLMapping(id = "excluirDespesa", pattern = "/despesa/excluir/#{id:manterDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/despesa/manterDespesa.jsf")})
public class ManterDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = 64293476369101592L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DESPESA_VIEW = "pretty:alterarDespesa";

	public static final String VISUALIZAR_DESPESA_VIEW = "pretty:visualizarDespesa";

	public static final String EXCLUIR_DESPESA_VIEW = "pretty:excluirDespesa";

	public static final String PERMISSAO_INCLUIR_DESPESA = "incluir.despesa";

	public static final String PERMISSAO_ALTERAR_DESPESA = "alterar.despesa";

	public static final String PERMISSAO_EXCLUIR_DESPESA = "excluir.despesa";

	private Long id;

	private Despesa despesa;

	private List<DetalheProvisaoDespesa> listaDetalheProvisaoDespesa;

	@Inject
	private DetalheProvisaoDespesaService detalheProvisaoDespesaService;

	@Inject
	private DespesaService despesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		despesa = new Despesa();
		LOGGER.debug("Carregando lista de DetalheProvisaoDespesa");
		listaDetalheProvisaoDespesa = detalheProvisaoDespesaService.findAll();
	}

	@URLAction(mappingId = "incluirDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando Despesa: {}", getId());
		despesa = despesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo Despesa: {}", despesa);
		despesaService.saveOrUpdate(despesa);
		if (despesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo Despesa: {}", getId());
		despesaService.delete(getId());
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

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<DetalheProvisaoDespesa> getListaDetalheProvisaoDespesa() {
		return listaDetalheProvisaoDespesa;
	}

	public void setListaDetalheProvisaoDespesa(List<DetalheProvisaoDespesa> listaDetalheProvisaoDespesa) {
		this.listaDetalheProvisaoDespesa = listaDetalheProvisaoDespesa;
	}

}
