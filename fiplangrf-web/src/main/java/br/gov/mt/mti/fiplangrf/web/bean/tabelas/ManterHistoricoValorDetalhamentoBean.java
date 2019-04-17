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
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorDetalhamento;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoService;
import br.gov.mt.mti.fiplangrf.service.tabelas.HistoricoValorDetalhamentoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterHistoricoValorDetalhamentoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirHistoricoValorDetalhamento", pattern = "/historicovalordetalhamento/incluir", viewId = "/pages/tabelas/historicoValorDetalhamento/manterHistoricoValorDetalhamento.jsf"),
		@URLMapping(id = "alterarHistoricoValorDetalhamento", pattern = "/historicovalordetalhamento/alterar/#{id:manterHistoricoValorDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorDetalhamento/manterHistoricoValorDetalhamento.jsf"),
		@URLMapping(id = "visualizarHistoricoValorDetalhamento", pattern = "/historicovalordetalhamento/visualizar/#{id:manterHistoricoValorDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorDetalhamento/manterHistoricoValorDetalhamento.jsf"),
		@URLMapping(id = "excluirHistoricoValorDetalhamento", pattern = "/historicovalordetalhamento/excluir/#{id:manterHistoricoValorDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/historicoValorDetalhamento/manterHistoricoValorDetalhamento.jsf")})
public class ManterHistoricoValorDetalhamentoBean extends AbstractManterBean {

	private static final long serialVersionUID = 424878584947442279L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_HISTORICOVALORDETALHAMENTO_VIEW = "pretty:alterarHistoricoValorDetalhamento";

	public static final String VISUALIZAR_HISTORICOVALORDETALHAMENTO_VIEW = "pretty:visualizarHistoricoValorDetalhamento";

	public static final String EXCLUIR_HISTORICOVALORDETALHAMENTO_VIEW = "pretty:excluirHistoricoValorDetalhamento";

	public static final String PERMISSAO_INCLUIR_HISTORICOVALORDETALHAMENTO = "incluir.historicoValorDetalhamento";

	public static final String PERMISSAO_ALTERAR_HISTORICOVALORDETALHAMENTO = "alterar.historicoValorDetalhamento";

	public static final String PERMISSAO_EXCLUIR_HISTORICOVALORDETALHAMENTO = "excluir.historicoValorDetalhamento";

	private Long id;

	private HistoricoValorDetalhamento historicoValorDetalhamento;

	private List<Detalhamento> listaDetalhamento;

	@Inject
	private DetalhamentoService detalhamentoService;

	@Inject
	private HistoricoValorDetalhamentoService historicoValorDetalhamentoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		historicoValorDetalhamento = new HistoricoValorDetalhamento();
		LOGGER.debug("Carregando lista de Detalhamento");
		listaDetalhamento = detalhamentoService.findAll();
	}

	@URLAction(mappingId = "incluirHistoricoValorDetalhamento", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_HISTORICOVALORDETALHAMENTO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarHistoricoValorDetalhamento", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_HISTORICOVALORDETALHAMENTO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarHistoricoValorDetalhamento", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirHistoricoValorDetalhamento", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_HISTORICOVALORDETALHAMENTO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando HistoricoValorDetalhamento: {}", getId());
		historicoValorDetalhamento = historicoValorDetalhamentoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = historicoValorDetalhamento.getId() == null;
		LOGGER.debug("Persistindo HistoricoValorDetalhamento: {}", historicoValorDetalhamento);
		historicoValorDetalhamentoService.saveOrUpdate(historicoValorDetalhamento);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo HistoricoValorDetalhamento: {}", getId());
		historicoValorDetalhamentoService.delete(getId());
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

	public HistoricoValorDetalhamento getHistoricoValorDetalhamento() {
		return historicoValorDetalhamento;
	}

	public void setHistoricoValorDetalhamento(HistoricoValorDetalhamento historicoValorDetalhamento) {
		this.historicoValorDetalhamento = historicoValorDetalhamento;
	}

	public List<Detalhamento> getListaDetalhamento() {
		return listaDetalhamento;
	}

	public void setListaDetalhamento(List<Detalhamento> listaDetalhamento) {
		this.listaDetalhamento = listaDetalhamento;
	}

}
