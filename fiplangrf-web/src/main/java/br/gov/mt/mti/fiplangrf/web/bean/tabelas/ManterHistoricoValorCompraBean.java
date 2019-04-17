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
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorCompra;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.service.tabelas.HistoricoValorCompraService;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterHistoricoValorCompraBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirHistoricoValorCompra", pattern = "/historicovalorcompra/incluir", viewId = "/pages/tabelas/historicoValorCompra/manterHistoricoValorCompra.jsf"),
		@URLMapping(id = "alterarHistoricoValorCompra", pattern = "/historicovalorcompra/alterar/#{id:manterHistoricoValorCompraBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorCompra/manterHistoricoValorCompra.jsf"),
		@URLMapping(id = "visualizarHistoricoValorCompra", pattern = "/historicovalorcompra/visualizar/#{id:manterHistoricoValorCompraBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorCompra/manterHistoricoValorCompra.jsf"),
		@URLMapping(id = "excluirHistoricoValorCompra", pattern = "/historicovalorcompra/excluir/#{id:manterHistoricoValorCompraBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorCompra/manterHistoricoValorCompra.jsf")})
public class ManterHistoricoValorCompraBean extends AbstractManterBean {

	private static final long serialVersionUID = -465015392910327700L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_HISTORICOVALORCOMPRA_VIEW = "pretty:alterarHistoricoValorCompra";

	public static final String VISUALIZAR_HISTORICOVALORCOMPRA_VIEW = "pretty:visualizarHistoricoValorCompra";

	public static final String EXCLUIR_HISTORICOVALORCOMPRA_VIEW = "pretty:excluirHistoricoValorCompra";

	public static final String PERMISSAO_INCLUIR_HISTORICOVALORCOMPRA = "incluir.historicoValorCompra";

	public static final String PERMISSAO_ALTERAR_HISTORICOVALORCOMPRA = "alterar.historicoValorCompra";

	public static final String PERMISSAO_EXCLUIR_HISTORICOVALORCOMPRA = "excluir.historicoValorCompra";

	private Long id;

	private HistoricoValorCompra historicoValorCompra;

	private List<RecursoFinanceiro> listaRecursoFinanceiro;

	@Inject
	private RecursoFinanceiroService recursoFinanceiroService;

	@Inject
	private HistoricoValorCompraService historicoValorCompraService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		historicoValorCompra = new HistoricoValorCompra();
		LOGGER.debug("Carregando lista de RecursoFinanceiro");
		listaRecursoFinanceiro = recursoFinanceiroService.findAll();
	}

	@URLAction(mappingId = "incluirHistoricoValorCompra", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_HISTORICOVALORCOMPRA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarHistoricoValorCompra", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_HISTORICOVALORCOMPRA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarHistoricoValorCompra", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirHistoricoValorCompra", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_HISTORICOVALORCOMPRA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando HistoricoValorCompra: {}", getId());
		historicoValorCompra = historicoValorCompraService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = historicoValorCompra.getId() == null;
		LOGGER.debug("Persistindo HistoricoValorCompra: {}", historicoValorCompra);
		historicoValorCompraService.saveOrUpdate(historicoValorCompra);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo HistoricoValorCompra: {}", getId());
		historicoValorCompraService.delete(getId());
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

	public HistoricoValorCompra getHistoricoValorCompra() {
		return historicoValorCompra;
	}

	public void setHistoricoValorCompra(HistoricoValorCompra historicoValorCompra) {
		this.historicoValorCompra = historicoValorCompra;
	}

	public List<RecursoFinanceiro> getListaRecursoFinanceiro() {
		return listaRecursoFinanceiro;
	}

	public void setListaRecursoFinanceiro(List<RecursoFinanceiro> listaRecursoFinanceiro) {
		this.listaRecursoFinanceiro = listaRecursoFinanceiro;
	}

}
