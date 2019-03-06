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
import br.gov.mt.mti.fiplangrf.model.tabelas.DocumentoFinanceiro;
import br.gov.mt.mti.fiplangrf.service.tabelas.DocumentoFinanceiroService;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

@Named("manterDocumentoFinanceiroBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDocumentoFinanceiro", pattern = "/documentofinanceiro/incluir", viewId = "/pages/tabelas/documentoFinanceiro/manterDocumentoFinanceiro.jsf"),
		@URLMapping(id = "alterarDocumentoFinanceiro", pattern = "/documentofinanceiro/alterar/#{id:manterDocumentoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/documentoFinanceiro/manterDocumentoFinanceiro.jsf"),
		@URLMapping(id = "visualizarDocumentoFinanceiro", pattern = "/documentofinanceiro/visualizar/#{id:manterDocumentoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/documentoFinanceiro/manterDocumentoFinanceiro.jsf"),
		@URLMapping(id = "excluirDocumentoFinanceiro", pattern = "/documentofinanceiro/excluir/#{id:manterDocumentoFinanceiroBean.idCriptogradado}", viewId = "/pages/tabelas/documentoFinanceiro/manterDocumentoFinanceiro.jsf")})
public class ManterDocumentoFinanceiroBean extends AbstractManterBean {

	private static final long serialVersionUID = -545974575234390395L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DOCUMENTOFINANCEIRO_VIEW = "pretty:alterarDocumentoFinanceiro";

	public static final String VISUALIZAR_DOCUMENTOFINANCEIRO_VIEW = "pretty:visualizarDocumentoFinanceiro";

	public static final String EXCLUIR_DOCUMENTOFINANCEIRO_VIEW = "pretty:excluirDocumentoFinanceiro";

	public static final String PERMISSAO_INCLUIR_DOCUMENTOFINANCEIRO = "incluir.documentoFinanceiro";

	public static final String PERMISSAO_ALTERAR_DOCUMENTOFINANCEIRO = "alterar.documentoFinanceiro";

	public static final String PERMISSAO_EXCLUIR_DOCUMENTOFINANCEIRO = "excluir.documentoFinanceiro";

	private Long id;

	private DocumentoFinanceiro documentoFinanceiro;

	private List<Detalhamento> listaDetalhamento;

	@Inject
	private DetalhamentoService detalhamentoService;

	@Inject
	private DocumentoFinanceiroService documentoFinanceiroService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		documentoFinanceiro = new DocumentoFinanceiro();
		LOGGER.debug("Carregando lista de Detalhamento");
		listaDetalhamento = detalhamentoService.findAll();
	}

	@URLAction(mappingId = "incluirDocumentoFinanceiro", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DOCUMENTOFINANCEIRO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDocumentoFinanceiro", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DOCUMENTOFINANCEIRO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDocumentoFinanceiro", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDocumentoFinanceiro", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DOCUMENTOFINANCEIRO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando DocumentoFinanceiro: {}", getId());
		documentoFinanceiro = documentoFinanceiroService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = documentoFinanceiro.getId() == null;
		LOGGER.debug("Persistindo DocumentoFinanceiro: {}", documentoFinanceiro);
		documentoFinanceiroService.saveOrUpdate(documentoFinanceiro);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo DocumentoFinanceiro: {}", getId());
		documentoFinanceiroService.delete(getId());
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

	public DocumentoFinanceiro getDocumentoFinanceiro() {
		return documentoFinanceiro;
	}

	public void setDocumentoFinanceiro(DocumentoFinanceiro documentoFinanceiro) {
		this.documentoFinanceiro = documentoFinanceiro;
	}

	public List<Detalhamento> getListaDetalhamento() {
		return listaDetalhamento;
	}

	public void setListaDetalhamento(List<Detalhamento> listaDetalhamento) {
		this.listaDetalhamento = listaDetalhamento;
	}

}
