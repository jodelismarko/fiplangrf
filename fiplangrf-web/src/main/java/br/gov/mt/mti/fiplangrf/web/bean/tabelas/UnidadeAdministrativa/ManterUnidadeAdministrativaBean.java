package br.gov.mt.mti.fiplangrf.web.bean.tabelas.UnidadeAdministrativa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
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
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoAtividade;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAE;
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAEUnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.model.tabelas.LegislacaoUnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.service.integracao.fiplan.FIPLANUnidadeOrcamentariaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.CNAEService;
import br.gov.mt.mti.fiplangrf.service.tabelas.NaturezaJuridicaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoAdministracaoService;
import br.gov.mt.mti.fiplangrf.service.tabelas.UnidadeAdministrativaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterUnidadeAdministrativaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirUnidadeAdministrativa", pattern = "/unidadeadministrativa/incluir", viewId = "/pages/tabelas/unidadeAdministrativa/manterUnidadeAdministrativa.jsf"),
		@URLMapping(id = "alterarUnidadeAdministrativa", pattern = "/unidadeadministrativa/alterar/#{id:manterUnidadeAdministrativaBean.idCriptogradado}", viewId = "/pages/tabelas/unidadeAdministrativa/manterUnidadeAdministrativa.jsf"),
		@URLMapping(id = "visualizarUnidadeAdministrativa", pattern = "/unidadeadministrativa/visualizar/#{id:manterUnidadeAdministrativaBean.idCriptogradado}", viewId = "/pages/tabelas/unidadeAdministrativa/manterUnidadeAdministrativa.jsf"),
		@URLMapping(id = "excluirUnidadeAdministrativa", pattern = "/unidadeadministrativa/excluir/#{id:manterUnidadeAdministrativaBean.idCriptogradado}", viewId = "/pages/tabelas/unidadeAdministrativa/manterUnidadeAdministrativa.jsf"),
		@URLMapping(id = "incluirLegislativoUA", pattern = "/unidadeadministrativa/incluir/popup/legislativo", viewId = "/pages/tabelas/unidadeAdministrativa/popup/legislativoUAPopup.jsf") })
public class ManterUnidadeAdministrativaBean extends AbstractManterBean {

	private static final long serialVersionUID = 414125651825527710L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_UNIDADEADMINISTRATIVA_VIEW = "pretty:alterarUnidadeAdministrativa";

	public static final String VISUALIZAR_UNIDADEADMINISTRATIVA_VIEW = "pretty:visualizarUnidadeAdministrativa";

	public static final String EXCLUIR_UNIDADEADMINISTRATIVA_VIEW = "pretty:excluirUnidadeAdministrativa";

	public static final String PERMISSAO_INCLUIR_UNIDADEADMINISTRATIVA = "incluir.unidadeAdministrativa";

	public static final String PERMISSAO_ALTERAR_UNIDADEADMINISTRATIVA = "alterar.unidadeAdministrativa";

	public static final String PERMISSAO_EXCLUIR_UNIDADEADMINISTRATIVA = "excluir.unidadeAdministrativa";

	public static final String PERMISSAO_INCLUIR_LEGISLATIVOUA = "incluir.legislativoUA";

	private static final String DLG_CNAE_FORM = "dlgFormCnae";
	private static final String DLG_LEGISLACAO_FORM = "dlgFormLegislacao";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private UnidadeAdministrativa unidadeAdministrativa;

	@Getter
	@Setter
	private CNAEUnidadeAdministrativa cnaeUA;

	@Getter
	@Setter
	private LegislacaoUnidadeAdministrativa legislacaoUA;

	@Inject
	private TipoAdministracaoService tipoAdministracaoService;

	@Inject
	private NaturezaJuridicaService naturezaJuridicaService;

	@Inject
	private FIPLANUnidadeOrcamentariaService fiplanUnidadeOrcamentariaService;

	@Inject
	private UnidadeAdministrativaService unidadeAdministrativaService;

	@Inject
	private CNAEService cnaeService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		cnaeUA = new CNAEUnidadeAdministrativa();
		legislacaoUA = new LegislacaoUnidadeAdministrativa();
		unidadeAdministrativa = new UnidadeAdministrativa();
		unidadeAdministrativa.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
	}

	@URLAction(mappingId = "incluirUnidadeAdministrativa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_UNIDADEADMINISTRATIVA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarUnidadeAdministrativa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_UNIDADEADMINISTRATIVA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarUnidadeAdministrativa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirUnidadeAdministrativa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_UNIDADEADMINISTRATIVA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando UnidadeAdministrativa: {}", getId());
		unidadeAdministrativa = unidadeAdministrativaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = unidadeAdministrativa.getId() == null;
		LOGGER.debug("Persistindo UnidadeAdministrativa: {}", unidadeAdministrativa);

		try {

			unidadeAdministrativa = unidadeAdministrativaService.incluir(unidadeAdministrativa);
			
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		
		} catch (BusinessException e) {
		
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		
		}

		this.setId(unidadeAdministrativa.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo UnidadeAdministrativa: {}", getId());
		unidadeAdministrativaService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}

	public void refreshTipoPoder() {
		unidadeAdministrativa.setTipoAdministracao(null);
	}

	public List<TipoAdministracao> autoCompleteTipoAdministracao(String query) {
		if (unidadeAdministrativa.getTipoPoder() == null) {
			return new ArrayList<TipoAdministracao>();
		}
		List<TipoAdministracao> todas = tipoAdministracaoService.findByName(query);
		List<TipoAdministracao> filtradas = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			TipoAdministracao skin = todas.get(i);
			if (skin.getTipoPoder().equals(unidadeAdministrativa.getTipoPoder())) {
				filtradas.add(skin);
			}
		}
		Collections.sort(filtradas);
		return filtradas;
	}

	public List<NaturezaJuridica> autoCompleteNaturezaJuridica(String query) {
		if (unidadeAdministrativa.getTipoNaturezaJuridica() == null) {
			return new ArrayList<NaturezaJuridica>();
		}
		List<NaturezaJuridica> todas = naturezaJuridicaService.findByName(query);
		List<NaturezaJuridica> filtradas = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			NaturezaJuridica skin = todas.get(i);
			if (skin.getTipoNaturezaJuridica().equals(unidadeAdministrativa.getTipoNaturezaJuridica())) {
				filtradas.add(skin);
			}
		}
		Collections.sort(filtradas);
		return filtradas;
	}
	
	public void refreshTipoNaturezaJuridica() {
		unidadeAdministrativa.setNaturezaJuridica(null);
	}

	public List<FIPLANUnidadeOrcamentaria> autoCompleteUnidadeOrcamentaria(String query) {
		List<FIPLANUnidadeOrcamentaria> filtradas = fiplanUnidadeOrcamentariaService.findByCodSugest(query);
		Collections.sort(filtradas);
		return filtradas;
	}
	
	public void refreshUnidadeOrcamentaria() {
		unidadeAdministrativa.setUnidadeOrcamentaria(null);
		unidadeAdministrativa.setFiplanUnidadeOrcamentaria(null);
	}

	/*
	 * Métodos para Ação Coordenadoria
	 */
	public void addNewCnae(ActionEvent event) {
		setCnaeUA(new CNAEUnidadeAdministrativa());
		setModoOperacao(TipoModoOperacao.INCLUSAO);
		cnaeUA.setUnidadeAdministrativa(unidadeAdministrativa);

		if (this.unidadeAdministrativa.existeCnaePrincipal()) {
			cnaeUA.setFlagTipoAtividade(DominioTipoAtividade.SECUNDARIA);
		}

		openDialog(DLG_CNAE_FORM);
	}

	public void incluirCnae(ActionEvent event) {

		if (unidadeAdministrativa.isCNAECadastrato(cnaeUA)) {
			addGenericMessage(DLG_CNAE_FORM, "CNAE já cadastra na Unidade Administrativa.", FacesMessage.SEVERITY_WARN);
			return;
		}

		if (unidadeAdministrativa.existeCnaePrincipal()) {
			cnaeUA.getFlagTipoAtividade();
		}

		unidadeAdministrativa.getCnaes().add(cnaeUA);
		atualizarComponente("@([id$=tableCnae])");

		closeDialog(DLG_CNAE_FORM);
	}
	
	public void limparCnae(ActionEvent event) {
		setCnaeUA(new CNAEUnidadeAdministrativa());
		
		if (this.unidadeAdministrativa.existeCnaePrincipal()) {
			cnaeUA.setFlagTipoAtividade(DominioTipoAtividade.SECUNDARIA);
		}
		
		atualizarComponente("@([id$=dlgFormCnae])");
	}

	public void excluirCnae(CNAEUnidadeAdministrativa cnaeUA) {
		unidadeAdministrativa.getCnaes().remove(cnaeUA);
		atualizarComponente("@([id$=tableCnae])");
	}

	public void visualizarCnae(CNAEUnidadeAdministrativa cnaeUA) {
		this.cnaeUA = cnaeUA;
		setModoOperacao(TipoModoOperacao.VISUALIZACAO);
		openDialog(DLG_CNAE_FORM);
	}

	public void editarCnae(CNAEUnidadeAdministrativa cnaeUA) {
		this.cnaeUA = cnaeUA;
		setModoOperacao(TipoModoOperacao.ALTERACAO);
		openDialog(DLG_CNAE_FORM);
	}

	public void closeCnae(ActionEvent event) {
		if (getUnidadeAdministrativa().getCnaes().stream().filter(cnae -> cnae.isTipoAtividadePrincipal() && cnae.getSituacao().equals(DominioSituacao.ATIVO)).count() > 1L) {
			addGenericMessage(DLG_CNAE_FORM, "Já existe uma CNAE principal ATIVO cadastrado. Para alterar o CNAE principal, inative o anterior.", FacesMessage.SEVERITY_WARN);
			getCnaeUA().setSituacao(DominioSituacao.INATIVO);
			return;
		}
		closeDialog(DLG_CNAE_FORM);
	}

	public List<CNAE> autoCompleteCnae(String query) {
		return cnaeService.findByCodSugest(query);
	}

	/*
	 * Métodos para Legislação
	 */
	public void addNewLegislacao(ActionEvent event) {
		setLegislacaoUA(new LegislacaoUnidadeAdministrativa());
		setModoOperacao(TipoModoOperacao.INCLUSAO);
		legislacaoUA.setUnidadeAdministrativa(unidadeAdministrativa);
		openDialog(DLG_LEGISLACAO_FORM);
	}

	public void incluirLegislacao(ActionEvent event) {
		unidadeAdministrativa.getLegislacaoUAs().add(legislacaoUA);
		atualizarComponente("@([id$=tableAcaoCoordenadoria])");
		closeDialog(DLG_LEGISLACAO_FORM);
	}
	
	public void limparLegislacao(ActionEvent event) {
		setLegislacaoUA(new LegislacaoUnidadeAdministrativa());
		atualizarComponente("@([id$=dlgFormLegislacao])");
	}

	public void excluirLegislacao(LegislacaoUnidadeAdministrativa legislacao) {
		unidadeAdministrativa.getLegislacaoUAs().remove(legislacao);
	}

	public void visualizarLegislacao(LegislacaoUnidadeAdministrativa legislacao) {
		this.legislacaoUA = legislacao;
		setModoOperacao(TipoModoOperacao.VISUALIZACAO);
		openDialog(DLG_LEGISLACAO_FORM);
	}

	public void editarLegislacao(LegislacaoUnidadeAdministrativa legislacao) {
		this.legislacaoUA = legislacao;
		setModoOperacao(TipoModoOperacao.ALTERACAO);
		openDialog(DLG_LEGISLACAO_FORM);
	}

	public void closeLegislacao(ActionEvent event) {
		closeDialog(DLG_LEGISLACAO_FORM);
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}
}
