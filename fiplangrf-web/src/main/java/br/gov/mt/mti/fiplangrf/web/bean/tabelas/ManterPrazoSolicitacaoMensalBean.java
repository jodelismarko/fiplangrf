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
import br.gov.mt.mti.fiplangrf.model.tabelas.PrazoSolicitacaoMensal;
import br.gov.mt.mti.fiplangrf.service.tabelas.PrazoSolicitacaoMensalService;
import br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;

@Named("manterPrazoSolicitacaoMensalBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirPrazoSolicitacaoMensal", pattern = "/prazosolicitacaomensal/incluir", viewId = "/pages/tabelas/prazoSolicitacaoMensal/manterPrazoSolicitacaoMensal.jsf"),
		@URLMapping(id = "alterarPrazoSolicitacaoMensal", pattern = "/prazosolicitacaomensal/alterar/#{id:manterPrazoSolicitacaoMensalBean.idCriptogradado}", viewId = "/pages/tabelas/prazoSolicitacaoMensal/manterPrazoSolicitacaoMensal.jsf"),
		@URLMapping(id = "visualizarPrazoSolicitacaoMensal", pattern = "/prazosolicitacaomensal/visualizar/#{id:manterPrazoSolicitacaoMensalBean.idCriptogradado}", viewId = "/pages/tabelas/prazoSolicitacaoMensal/manterPrazoSolicitacaoMensal.jsf"),
		@URLMapping(id = "excluirPrazoSolicitacaoMensal", pattern = "/prazosolicitacaomensal/excluir/#{id:manterPrazoSolicitacaoMensalBean.idCriptogradado}", viewId = "/pages/tabelas/prazoSolicitacaoMensal/manterPrazoSolicitacaoMensal.jsf")})
public class ManterPrazoSolicitacaoMensalBean extends AbstractManterBean {

	private static final long serialVersionUID = 439631888911188982L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_PRAZOSOLICITACAOMENSAL_VIEW = "pretty:alterarPrazoSolicitacaoMensal";

	public static final String VISUALIZAR_PRAZOSOLICITACAOMENSAL_VIEW = "pretty:visualizarPrazoSolicitacaoMensal";

	public static final String EXCLUIR_PRAZOSOLICITACAOMENSAL_VIEW = "pretty:excluirPrazoSolicitacaoMensal";

	public static final String PERMISSAO_INCLUIR_PRAZOSOLICITACAOMENSAL = "incluir.prazoSolicitacaoMensal";

	public static final String PERMISSAO_ALTERAR_PRAZOSOLICITACAOMENSAL = "alterar.prazoSolicitacaoMensal";

	public static final String PERMISSAO_EXCLUIR_PRAZOSOLICITACAOMENSAL = "excluir.prazoSolicitacaoMensal";

	private Long id;

	private PrazoSolicitacaoMensal prazoSolicitacaoMensal;

	private List<PlanejamentoAnualPrazos> listaPlanAnualPrazo;

	@Inject
	private PlanejamentoAnualPrazosService planejamentoAnualPrazosService;

	@Inject
	private PrazoSolicitacaoMensalService prazoSolicitacaoMensalService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		prazoSolicitacaoMensal = new PrazoSolicitacaoMensal();
		LOGGER.debug("Carregando lista de PlanejamentoAnualPrazos");
		listaPlanAnualPrazo = planejamentoAnualPrazosService.findAll();
	}

	@URLAction(mappingId = "incluirPrazoSolicitacaoMensal", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_PRAZOSOLICITACAOMENSAL);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarPrazoSolicitacaoMensal", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_PRAZOSOLICITACAOMENSAL);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarPrazoSolicitacaoMensal", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirPrazoSolicitacaoMensal", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_PRAZOSOLICITACAOMENSAL);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando PrazoSolicitacaoMensal: {}", getId());
		prazoSolicitacaoMensal = prazoSolicitacaoMensalService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo PrazoSolicitacaoMensal: {}", prazoSolicitacaoMensal);
		prazoSolicitacaoMensalService.saveOrUpdate(prazoSolicitacaoMensal);
		if (prazoSolicitacaoMensal.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo PrazoSolicitacaoMensal: {}", getId());
		prazoSolicitacaoMensalService.delete(getId());
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

	public PrazoSolicitacaoMensal getPrazoSolicitacaoMensal() {
		return prazoSolicitacaoMensal;
	}

	public void setPrazoSolicitacaoMensal(PrazoSolicitacaoMensal prazoSolicitacaoMensal) {
		this.prazoSolicitacaoMensal = prazoSolicitacaoMensal;
	}

	public List<PlanejamentoAnualPrazos> getListaPlanAnualPrazo() {
		return listaPlanAnualPrazo;
	}

	public void setListaPlanAnualPrazo(List<PlanejamentoAnualPrazos> listaPlanAnualPrazo) {
		this.listaPlanAnualPrazo = listaPlanAnualPrazo;
	}

}
