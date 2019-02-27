package br.gov.mt.mti.fiplangrf.web.bean.tabelas.Ocorrencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.common.util.CriptografiaUtil;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.AcaoCoordenadoria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.model.tabelas.EncerramentoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.MedidaIntermediaria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Ocorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.SubGrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TramiteOcorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.service.integracao.fiplan.FIPLANUnidadeOrcamentariaService;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.OcorrenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.SubGrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TramiteOcorrenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.UnidadeAdministrativaService;
import br.gov.mt.mti.fiplangrf.util.converter.FiplanConverter;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.bean.helper.AnexoBeanHelper;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Named("manterOcorrenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirOcorrencia", pattern = "/ocorrencia/incluir", viewId = "/pages/tabelas/ocorrencia/manterOcorrencia.jsf"),
		@URLMapping(id = "alterarOcorrencia", pattern = "/ocorrencia/alterar/#{id:manterOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/ocorrencia/manterOcorrencia.jsf"),
		@URLMapping(id = "visualizarOcorrencia", pattern = "/ocorrencia/visualizar/#{id:manterOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/ocorrencia/manterOcorrencia.jsf"),
		@URLMapping(id = "excluirOcorrencia", pattern = "/ocorrencia/excluir/#{id:manterOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/ocorrencia/manterOcorrencia.jsf") })
public class ManterOcorrenciaBean extends AbstractManterBean {

	private static final long serialVersionUID = -18496490086721196L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_OCORRENCIA_VIEW = "pretty:alterarOcorrencia";

	public static final String VISUALIZAR_OCORRENCIA_VIEW = "pretty:visualizarOcorrencia";

	public static final String EXCLUIR_OCORRENCIA_VIEW = "pretty:excluirOcorrencia";

	public static final String PERMISSAO_INCLUIR_OCORRENCIA = "incluir.ocorrencia";

	public static final String PERMISSAO_ALTERAR_OCORRENCIA = "alterar.ocorrencia";

	public static final String PERMISSAO_EXCLUIR_OCORRENCIA = "excluir.ocorrencia";

	private static final String DLG_ACAO_COORDENADORIA_FORM = "dlgFormAcaoCoordenadoria";

	private static final String DLG_MEDIDA_INTERMEDIARIA_FORM = "dlgFormMedidaIntermediaria";

	private static final String DLG_ENC_PENDENCIA_FORM = "dlgFormEncerramentoPendencia";

	private static final String DLG_SUSPENDER_FORM = "dlgSuspender";

	private static final String DLG_REATIVAR_FORM = "dlgReativar";

	private static final String DLG_VOLTAR_REGULARIZACAO_FORM = "dlgVoltarRegularizacao";

	private static final String DLG_VOLTAR_ELABORACAO_FORM = "dlgVoltarElaboracao";

	private static final String DLG_FINALIZAR_FORM = "dlgFinalizar";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Ocorrencia ocorrencia;

	@Getter
	@Setter
	private UnidadeAdministrativa unidadeAdministrativa;

	@Getter
	@Setter
	private UnidadeOrcamentaria unidadeOrcamentaria;

	@Getter
	private List<TipoOcorrencia> listaTipoOcorrencia;
	
	@Getter
	private List<TramiteOcorrencia> tramiteList = new ArrayList<>();

	@Getter
	@Setter
	private AcaoCoordenadoria acaoCoordenadoria;

	@Getter
	@Setter
	private MedidaIntermediaria medidaIntermediaria;

	@Getter
	@Setter
	private EncerramentoPendencia encerramentoPendencia;

	/*
	 * Services Utilizados
	 */
	@Inject
	private UnidadeAdministrativaService unidadeAdministrativaService;

	@Inject
	private TipoOcorrenciaService tipoOcorrenciaService;

	@Inject
	private GrupoPendenciaService grupoPendenciaService;
	List<GrupoPendencia> filtradasGrupoPendencia = new ArrayList<>();

	@Inject
	private SubGrupoPendenciaService subGrupoPendenciaService;
	List<SubGrupoPendencia> filtradasSubGrupoPendencia = new ArrayList<>();

	@Inject
	private TipoPendenciaService tipoPendenciaService;
	List<TipoPendencia> filtradasTipoPendencia = new ArrayList<>();

	@Inject
	private OcorrenciaService ocorrenciaService;

	@Inject
	private TramiteOcorrenciaService tramiteOcorrenciaService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private FIPLANUnidadeOrcamentariaService fiplanUnidadeOrcamentariaService;

	@Getter
	@Setter
	Usuario usuarioLogadoEntity;

	@Inject
	@Getter
	@Setter
	private AnexoBeanHelper anexoBeanHelper;

	@PostConstruct
	public void inicializar() {
		usuarioLogadoEntity = usuarioService.findById(Env.getUsuarioLogado().getCodigo());
		LOGGER.debug("Carregando lista de TipoOcorrencia");
		listaTipoOcorrencia = tipoOcorrenciaService.findAllActive();
		limpar();
	}

	public void limpar() {
		setUnidadeAdministrativa(new UnidadeAdministrativa());
		setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		setAcaoCoordenadoria(new AcaoCoordenadoria());
		setMedidaIntermediaria(new MedidaIntermediaria());
		setEncerramentoPendencia(new EncerramentoPendencia());
		setOcorrencia(new Ocorrencia());
	}

	@URLAction(mappingId = "incluirOcorrencia", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_OCORRENCIA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarOcorrencia", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_OCORRENCIA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarOcorrencia", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirOcorrencia", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_OCORRENCIA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando Ocorrencia: {}", getId());
		setOcorrencia(ocorrenciaService.findByIdFetchAll(getId()));
		unidadeAdministrativa = getOcorrencia().getUnidadeAdministrativa();
		carregarTramite();
	}
	
	

	private void carregarTramite() {
		tramiteList = tramiteOcorrenciaService.findAllByIdOcorrencia(getId());
	}

	public void carregarUnidadeAdministrativa() {
		LOGGER.debug("Carregando Unidade Administrativa by CPNJ:");

		try {
			this.unidadeAdministrativa = unidadeAdministrativaService
					.findByCNPJ(getUnidadeAdministrativa().getNumrCNPJ());
			getOcorrencia().setUnidadeOrcamentaria(getUnidadeAdministrativa().getUnidadeOrcamentaria());
			validaUnidadeOrcamentaria();
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;

		} catch (NumberFormatException e) {
			showMainMsgDialog("Insira um CNPJ válido.", FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
	}

	private void validaUnidadeOrcamentaria() throws BusinessException {

		List<FIPLANUnidadeOrcamentaria> listaUnidadeOrcamentaria = new ArrayList<>();
		listaUnidadeOrcamentaria = (List<FIPLANUnidadeOrcamentaria>) fiplanUnidadeOrcamentariaService.findAll();
		if (unidadeAdministrativa.getUnidadeOrcamentaria() != null) {
			FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria = FiplanConverter
					.getFiplanFromUnidadeOrcamentaria(unidadeAdministrativa.getUnidadeOrcamentaria());
			if (!listaUnidadeOrcamentaria.contains(fiplanUnidadeOrcamentaria)) {
				throw new BusinessException(DominioMensagem.MSG_UO_INVALIDA.getDesc());
			}
		}

	}

	/*
	 * Métodos para Ocorrencia
	 */
	public void salvar() throws BusinessException {
		boolean inclusao = getOcorrencia().getId() == null;
		LOGGER.debug("Persistindo Ocorrencia: {}", getOcorrencia());

		try {
			getOcorrencia().setUnidadeAdministrativa(getUnidadeAdministrativa());

			/* Persistindo Ocorrência */
			setOcorrencia(ocorrenciaService.checkAndSave(getOcorrencia()));

			if (inclusao) {
				/* Persistindo Tramite de Registro */
				tramiteOcorrenciaService.registrar(getOcorrencia(), getUsuarioLogadoEntity());
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}

		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		this.setId(getOcorrencia().getId());
		carregarTramite();
		setModoVisualizacao();
	}

	/*
	 * Inicio Tramites
	 */
	public void enviarParaRegularizacao() throws BusinessException {
		tramiteOcorrenciaService.enviarParaRegularizacao(getOcorrencia(), getUsuarioLogadoEntity());
		carregar();
		atualizarComponente("@([id$=crudForm])");
		showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_ENVIADA_PARA_REGULARIZACAO.getDesc(),
				ButtonScript.CLOSE_MAIN_MSG_DLG);
	}

	public void devolverParaRegularizacao() throws BusinessException {
		if (StringUtils.isBlank(getOcorrencia().getJustificativaTramite())) {
			sendErrorDlg(DLG_VOLTAR_REGULARIZACAO_FORM,
					"Ao devoler a ocorrência para regularização é necessário informar uma justificativa.");
			return;
		}

		try {
			tramiteOcorrenciaService.devolverParaRegularizacao(getOcorrencia(), getUsuarioLogadoEntity());
			carregar();
			atualizarComponente("@([id$=crudForm])");
			showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_ENVIADA_PARA_REGULARIZACAO.getDesc(),
					ButtonScript.CLOSE_MAIN_MSG_DLG);
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		closeDialog(DLG_VOLTAR_REGULARIZACAO_FORM);
	}

	public void enviarParaAnalise() throws BusinessException {
		tramiteOcorrenciaService.enviarParaAnalise(getOcorrencia(), getUsuarioLogadoEntity());
		carregar();
		atualizarComponente("@([id$=crudForm])");
		showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_ENVIADA_PARA_ANALISE.getDesc(),
				ButtonScript.CLOSE_MAIN_MSG_DLG);
	}

	public void devolverParaElaboracao() throws BusinessException {
		if (StringUtils.isBlank(getOcorrencia().getJustificativaTramite())) {
			sendErrorDlg(DLG_VOLTAR_ELABORACAO_FORM,
					"Ao devoler a ocorrência para regularização é necessário informar uma justificativa.");
			return;
		}

		try {
			tramiteOcorrenciaService.devolverParaElaboracao(getOcorrencia(), getUsuarioLogadoEntity());
			carregar();
			atualizarComponente("@([id$=crudForm])");
			showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_ENVIADA_PARA_ELABORACAO.getDesc(),
					ButtonScript.CLOSE_MAIN_MSG_DLG);
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		closeDialog(DLG_VOLTAR_ELABORACAO_FORM);
	}

	public void suspender() {
		if (StringUtils.isBlank(getOcorrencia().getJustificativaTramite())) {
			sendErrorDlg(DLG_SUSPENDER_FORM, "Ao suspender a ocorrência é necessário informar uma justificativa.");
			return;
		}

		try {
			tramiteOcorrenciaService.suspender(getOcorrencia(), getUsuarioLogadoEntity());
			carregar();
			atualizarComponente("@([id$=crudForm])");
			showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_SUSPENSA.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		closeDialog(DLG_SUSPENDER_FORM);
	}

	public void reativar() throws BusinessException {

		if (StringUtils.isBlank(getOcorrencia().getJustificativaTramite())) {
			sendErrorDlg(DLG_REATIVAR_FORM, "Ao reativar a ocorrência é necessário informar uma justificativa.");
			return;
		}

		try {
			tramiteOcorrenciaService.reativar(getOcorrencia(), getUsuarioLogadoEntity());
			carregar();
			atualizarComponente("@([id$=crudForm])");
			showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_REATIVADA.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		closeDialog(DLG_REATIVAR_FORM);
	}

	public void finalizar() throws BusinessException {
		if (getOcorrencia().getDataRegularizacao().equals(null)) {
			sendErrorDlg(DLG_FINALIZAR_FORM,
					"Ao finalizar a ocorrência é necessário informar uma data de regularização.");
			return;
		}

		try {
			tramiteOcorrenciaService.finalizar(getOcorrencia(), getUsuarioLogadoEntity());
			carregar();
			atualizarComponente("@([id$=crudForm])");
			showMainMsgDialog(DominioMensagem.MSG_OCORRENCIA_REGULARIZADA.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		closeDialog(DLG_FINALIZAR_FORM);
	}
	/*
	 * Fim Tramites
	 */

	public void excluir() {
		LOGGER.debug("Excluindo Ocorrencia: {}", getOcorrencia());
		ocorrenciaService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}

	public void openPopup() {
		getOcorrencia().setJustificativaTramite(null);
	}

	/*
	 * Métodos para Auto Complete
	 */

	public List<FIPLANUnidadeOrcamentaria> autoCompleteUnidadeOrcamentaria(String query) {
		List<FIPLANUnidadeOrcamentaria> filtradas = fiplanUnidadeOrcamentariaService.findByCodSugest(query);
		Collections.sort(filtradas);
		return filtradas;
	}

	public boolean isPermitidoTrocarUO() {

		boolean validacao1 = !getModoOperacao().equals(TipoModoOperacao.ALTERACAO);

		boolean validacao2 = !getOcorrencia().isSituacaoPendente();

		return validacao1 || validacao2;

	}
	
	public boolean isPermitidoTrocarTipoOcorrencia() {

		boolean validacao1 = !getModoOperacao().equals(TipoModoOperacao.ALTERACAO) && !getModoOperacao().equals(TipoModoOperacao.INCLUSAO);

		boolean validacao2 = getOcorrencia().isSituacaoPendente();

		return validacao1 || !validacao2;

	}

	public List<TipoOcorrencia> autoCompleteTipoOcorrencia(String query) {
		List<TipoOcorrencia> filtradas = tipoOcorrenciaService.findByName(query);
		Collections.sort(filtradas);
		return filtradas;
	}

	public void refreshTipoOcorrencia() {
		ocorrencia.setGrupoPendencia(null);
		filtradasGrupoPendencia = new ArrayList<>();

		ocorrencia.setSubGrupoPendencia(null);
		filtradasSubGrupoPendencia = new ArrayList<>();

		ocorrencia.setTipoPendencia(null);
		filtradasTipoPendencia = new ArrayList<>();
	}

	public boolean isAutoCompleteGrupoPendenciaHabilitado() {
		autoCompleteGrupoPendencia("");
		return filtradasGrupoPendencia.isEmpty();
	}

	public List<GrupoPendencia> autoCompleteGrupoPendencia(String query) {
		if (getOcorrencia().getTipoOcorrencia() == null) {
			return new ArrayList<GrupoPendencia>();
		}
		List<GrupoPendencia> todas = grupoPendenciaService.findByName(query);
		filtradasGrupoPendencia = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			GrupoPendencia skin = todas.get(i);
			if (skin.getTipoOcorrencia().equals(getOcorrencia().getTipoOcorrencia())) {
				filtradasGrupoPendencia.add(skin);
			}
		}
		Collections.sort(filtradasGrupoPendencia);
		return filtradasGrupoPendencia;
	}

	public void refreshGrupoPendencia() {
		getOcorrencia().setSubGrupoPendencia(null);
		filtradasSubGrupoPendencia = new ArrayList<>();

		getOcorrencia().setTipoPendencia(null);
		filtradasTipoPendencia = new ArrayList<>();
	}

	public boolean isAutoCompleteSubGrupoPendenciaHabilitado() {
		autoCompleteSubGrupoPendencia("");
		return filtradasSubGrupoPendencia.isEmpty();
	}

	public List<SubGrupoPendencia> autoCompleteSubGrupoPendencia(String query) {
		if (getOcorrencia().getGrupoPendencia() == null) {
			return new ArrayList<SubGrupoPendencia>();
		}
		List<SubGrupoPendencia> todas = subGrupoPendenciaService.findByName(query);
		filtradasSubGrupoPendencia = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			SubGrupoPendencia skin = todas.get(i);
			if (skin.getGrupoPendencia().equals(getOcorrencia().getGrupoPendencia())) {
				filtradasSubGrupoPendencia.add(skin);
			}
		}
		Collections.sort(filtradasSubGrupoPendencia);
		return filtradasSubGrupoPendencia;
	}

	public void refreshSubGrupoPendencia() {
		getOcorrencia().setTipoPendencia(null);
		filtradasTipoPendencia = new ArrayList<>();
	}

	public boolean isAutoCompleteTipoPendenciaHabilitado() {
		autoCompleteTipoPendencia("");
		return filtradasTipoPendencia.isEmpty();
	}

	public List<TipoPendencia> autoCompleteTipoPendencia(String query) {
		if (getOcorrencia().getTipoOcorrencia() == null) {
			return new ArrayList<TipoPendencia>();
		}
		List<TipoPendencia> todas = tipoPendenciaService.findByName(query);
		filtradasTipoPendencia = new ArrayList<>();

		for (TipoPendencia tipoPendencia : todas) {
			if (filtradasGrupoPendencia.isEmpty() && filtradasSubGrupoPendencia.isEmpty()
					&& tipoPendencia.getTipoOcorrencia().equals(getOcorrencia().getTipoOcorrencia())) {
				filtradasTipoPendencia.add(tipoPendencia);
			}

			if (filtradasSubGrupoPendencia.isEmpty() && getOcorrencia().getGrupoPendencia() != null
					&& tipoPendencia.getGrupoPendencia() != null
					&& tipoPendencia.getGrupoPendencia().equals(getOcorrencia().getGrupoPendencia())) {
				filtradasTipoPendencia.add(tipoPendencia);
			}

			if (getOcorrencia().getSubGrupoPendencia() != null && tipoPendencia.getSubGrupoPendencia() != null
					&& tipoPendencia.getSubGrupoPendencia().equals(getOcorrencia().getSubGrupoPendencia())) {
				filtradasTipoPendencia.add(tipoPendencia);
			}
		}

		Collections.sort(filtradasTipoPendencia);
		return filtradasTipoPendencia;
	}

	/*
	 * Métodos para Ação Coordenadoria
	 */
	public void addNewAcaoCoordenadoria(ActionEvent event) {
		setAcaoCoordenadoria(new AcaoCoordenadoria());
		setModoOperacao(TipoModoOperacao.INCLUSAO);
		getAcaoCoordenadoria().setOcorrencia(getOcorrencia());
		openDialog(DLG_ACAO_COORDENADORIA_FORM);

	}

	public void incluirAcaoCoordenadoria(ActionEvent event) {
		if (StringUtils.isBlank(getAcaoCoordenadoria().getDescricao())) {
			sendErrorDlg(DLG_ACAO_COORDENADORIA_FORM, "A descrição deve ser informada.");
			return;
		}

		getOcorrencia().getListaAcaoCoordenadoria().add(getAcaoCoordenadoria());
		atualizarComponente("@([id$=tableAcaoCoordenadoria])");

		setAcaoCoordenadoria(new AcaoCoordenadoria());

		closeDialog(DLG_ACAO_COORDENADORIA_FORM);
	}
	
	public void limparAcaoCoordenadoria(ActionEvent event) {
		setAcaoCoordenadoria(new AcaoCoordenadoria());
		atualizarComponente("@([id$=dlgFormAcaoCoordenadoria])");
	}

	public void excluirAcaoCoordenadoria(AcaoCoordenadoria acaoCoordenadoria) {
		getOcorrencia().getListaAcaoCoordenadoria().remove(acaoCoordenadoria);
	}

	public void visualizarAcaoCoordenadoria(AcaoCoordenadoria acaoCoordenadoria) {
		setAcaoCoordenadoria(acaoCoordenadoria);
		setModoOperacao(TipoModoOperacao.VISUALIZACAO);
		openDialog(DLG_ACAO_COORDENADORIA_FORM);
	}

	public void editarAcaoCoordenadoria(AcaoCoordenadoria acaoCoordenadoria) {
		setAcaoCoordenadoria(acaoCoordenadoria);
		setModoOperacao(TipoModoOperacao.ALTERACAO);
		openDialog(DLG_ACAO_COORDENADORIA_FORM);
	}

	public void uploadAnexoAcaoCoordenadoria(FileUploadEvent event) {
		getAcaoCoordenadoria().getAnexos().add(anexoBeanHelper.uploadAnexo(event));
	}

	public void removerAnexoAcaoCoordenadoria(Anexo a) {
		getAcaoCoordenadoria().getAnexos().remove(a);
	}

	public void closeAcaoCoordenadoria(ActionEvent event) {
		closeDialog(DLG_ACAO_COORDENADORIA_FORM);
	}

	/*
	 * Métodos para Medida Intermediaria
	 */
	public void addNewMedidaIntermediaria(ActionEvent event) {
		setMedidaIntermediaria(new MedidaIntermediaria());
		setModoOperacao(TipoModoOperacao.INCLUSAO);
		getMedidaIntermediaria().setOcorrencia(ocorrencia);
		openDialog(DLG_MEDIDA_INTERMEDIARIA_FORM);
	}

	public void incluirMedidaIntermediaria(ActionEvent event) {

		if (StringUtils.isBlank(getMedidaIntermediaria().getDescricao())) {
			sendErrorDlg(DLG_MEDIDA_INTERMEDIARIA_FORM, "A descrição deve ser informada.");
			return;
		}

		if (StringUtils.isBlank(getMedidaIntermediaria().getNomeResponsavel())) {
			sendErrorDlg(DLG_MEDIDA_INTERMEDIARIA_FORM, "O responsável deve ser informada.");
			return;
		}

		if (StringUtils.isBlank(getMedidaIntermediaria().getCargoFuncao())) {
			sendErrorDlg(DLG_MEDIDA_INTERMEDIARIA_FORM, "O cargo/função deve ser informada.");
			return;
		}

		if (StringUtils.isBlank(getMedidaIntermediaria().getNumrTelefone())) {
			sendErrorDlg(DLG_MEDIDA_INTERMEDIARIA_FORM, "O telefone deve ser informada.");
			return;
		}

		if (StringUtils.isBlank(getMedidaIntermediaria().getEmail())) {
			sendErrorDlg(DLG_MEDIDA_INTERMEDIARIA_FORM, "O email deve ser informada.");
			return;
		}

		getOcorrencia().getListaMedidaIntermediaria().add(getMedidaIntermediaria());
		atualizarComponente("@([id$=tableMedidaIntermediaria])");

		setMedidaIntermediaria(new MedidaIntermediaria());

		closeDialog(DLG_MEDIDA_INTERMEDIARIA_FORM);
	}
	
	public void limparMedidaIntermediaria(ActionEvent event) {
		setMedidaIntermediaria(new MedidaIntermediaria());
		atualizarComponente("@([id$=dlgFormMedidaIntermediaria])");
	}

	public void excluirMedidaIntermediaria(MedidaIntermediaria medidaIntermediaria) {
		getOcorrencia().getListaMedidaIntermediaria().remove(medidaIntermediaria);
	}

	public void visualizar(MedidaIntermediaria medidaIntermediaria) {
		setMedidaIntermediaria(medidaIntermediaria);
		setModoOperacao(TipoModoOperacao.VISUALIZACAO);
		openDialog(DLG_MEDIDA_INTERMEDIARIA_FORM);
	}

	public void editarMedidaIntermediaria(MedidaIntermediaria medidaIntermediaria) {
		setMedidaIntermediaria(medidaIntermediaria);
		setModoOperacao(TipoModoOperacao.ALTERACAO);
		openDialog(DLG_MEDIDA_INTERMEDIARIA_FORM);
	}

	public void uploadAnexoMedidaIntermediaria(FileUploadEvent event) {
		getMedidaIntermediaria().getAnexos().add(anexoBeanHelper.uploadAnexo(event));
	}

	public void removerAnexoMedidaIntermediaria(Anexo a) {
		getMedidaIntermediaria().getAnexos().remove(a);
	}

	public void closeMedidaIntermediaria(ActionEvent event) {
		closeDialog(DLG_MEDIDA_INTERMEDIARIA_FORM);
	}

	/*
	 * Métodos para Encerramento Pendencia
	 */
	public void addNewEncerramentoPendencia(ActionEvent event) {
		setEncerramentoPendencia(new EncerramentoPendencia());
		setModoOperacao(TipoModoOperacao.INCLUSAO);
		getEncerramentoPendencia().setOcorrencia(getOcorrencia());
		openDialog(DLG_ENC_PENDENCIA_FORM);
	}

	public void incluirEncerramentoPendencia(ActionEvent event) {

		if (StringUtils.isBlank(getEncerramentoPendencia().getDescricao())) {
			sendErrorDlg(DLG_ENC_PENDENCIA_FORM, "A descrição deve ser informada.");
			return;
		}

		getOcorrencia().getListaEncerramentoPendencia().add(getEncerramentoPendencia());
		atualizarComponente("@([id$=tableEncerramentoPendencia])");

		setEncerramentoPendencia(new EncerramentoPendencia());

		closeDialog(DLG_ENC_PENDENCIA_FORM);
	}
	
	public void limparEncerramentoPendencia(ActionEvent event) {
		setEncerramentoPendencia(new EncerramentoPendencia());
		atualizarComponente("@([id$=dlgFormEncerramentoPendencia])");
	}

	public void excluirEncerramentoPendencia(EncerramentoPendencia encerramentoPendencia) {
		getOcorrencia().getListaEncerramentoPendencia().remove(encerramentoPendencia);
	}

	public void visualizarEncerramentoPendencia(EncerramentoPendencia encerramentoPendencia) {
		setEncerramentoPendencia(encerramentoPendencia);
		setModoOperacao(TipoModoOperacao.VISUALIZACAO);
		openDialog(DLG_ENC_PENDENCIA_FORM);
	}

	public void editarEncerramentoPendencia(EncerramentoPendencia encerramentoPendencia) {
		setEncerramentoPendencia(encerramentoPendencia);
		setModoOperacao(TipoModoOperacao.ALTERACAO);
		openDialog(DLG_ENC_PENDENCIA_FORM);
	}

	public void uploadAnexoEncerramentoPendencia(FileUploadEvent event) {
		getEncerramentoPendencia().getAnexos().add(anexoBeanHelper.uploadAnexo(event));
	}

	public void removerAnexoEncerramentoPendencia(Anexo a) {
		getEncerramentoPendencia().getAnexos().remove(a);
	}

	public void closeEncerramentoPendencia(ActionEvent event) {
		closeDialog(DLG_ENC_PENDENCIA_FORM);
	}

	/*
	 * Getters and Setters
	 */
	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}
}
