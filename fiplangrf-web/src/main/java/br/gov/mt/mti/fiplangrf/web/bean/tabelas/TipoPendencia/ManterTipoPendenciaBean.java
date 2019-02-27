package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoPendencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.SubGrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.SubGrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoPendenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterTipoPendenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirTipoPendencia", pattern = "/tipopendencia/incluir", viewId = "/pages/tabelas/tipoPendencia/manterTipoPendencia.jsf"),
		@URLMapping(id = "alterarTipoPendencia", pattern = "/tipopendencia/alterar/#{id:manterTipoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoPendencia/manterTipoPendencia.jsf"),
		@URLMapping(id = "visualizarTipoPendencia", pattern = "/tipopendencia/visualizar/#{id:manterTipoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoPendencia/manterTipoPendencia.jsf"),
		@URLMapping(id = "excluirTipoPendencia", pattern = "/tipopendencia/excluir/#{id:manterTipoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoPendencia/manterTipoPendencia.jsf") })
public class ManterTipoPendenciaBean extends AbstractManterBean {

	private static final long serialVersionUID = 822477209970267864L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_TIPOPENDENCIA_VIEW = "pretty:alterarTipoPendencia";

	public static final String VISUALIZAR_TIPOPENDENCIA_VIEW = "pretty:visualizarTipoPendencia";

	public static final String EXCLUIR_TIPOPENDENCIA_VIEW = "pretty:excluirTipoPendencia";

	public static final String PERMISSAO_INCLUIR_TIPOPENDENCIA = "incluir.tipoPendencia";

	public static final String PERMISSAO_ALTERAR_TIPOPENDENCIA = "alterar.tipoPendencia";

	public static final String PERMISSAO_EXCLUIR_TIPOPENDENCIA = "excluir.tipoPendencia";

	@Getter @Setter
	private Long id;

	@Getter @Setter
	private TipoPendencia tipoPendencia;

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

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		tipoPendencia = new TipoPendencia();
		refreshTipoOcorrencia();
		refreshGrupoPendencia();
		LOGGER.debug("Carregando lista de TipoOcorrencia");
	}

	@URLAction(mappingId = "incluirTipoPendencia", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_TIPOPENDENCIA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarTipoPendencia", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_TIPOPENDENCIA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarTipoPendencia", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirTipoPendencia", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_TIPOPENDENCIA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando TipoPendencia: {}", getId());
		tipoPendencia = tipoPendenciaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = tipoPendencia.getId() == null;

		LOGGER.debug("Persistindo TipoPendencia: {}", tipoPendencia);

		try {
			tipoPendencia = tipoPendenciaService.checkAndSave(tipoPendencia);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		setId(tipoPendencia.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo TipoPendencia: {}", getId());
		tipoPendenciaService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}
	
	/*
	 * Métodos para Auto Complete
	 */
	public List<TipoOcorrencia> autoCompleteTipoOcorrencia(String query) {
		List<TipoOcorrencia> filtradasTipoOcorrencia = tipoOcorrenciaService.findByName(query);
		Collections.sort(filtradasTipoOcorrencia);
		return filtradasTipoOcorrencia;
	}
	
	public void refreshTipoOcorrencia() {
		tipoPendencia.setGrupoPendencia(null);
		filtradasGrupoPendencia = new ArrayList<>();
		
		tipoPendencia.setSubGrupoPendencia(null);
		filtradasSubGrupoPendencia = new ArrayList<>();
	}
	
	public boolean isAutoCompletGrupoPendenciaHabilitado() {
		autoCompleteGrupoPendencia("");
		return filtradasGrupoPendencia !=null && filtradasGrupoPendencia.isEmpty();
	}
	
	public List<GrupoPendencia> autoCompleteGrupoPendencia(String query) {
		if (tipoPendencia.getTipoOcorrencia() == null) {
			return new ArrayList<GrupoPendencia>();
		}
		List<GrupoPendencia> todas = grupoPendenciaService.findByName(query);
		filtradasGrupoPendencia = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			GrupoPendencia skin = todas.get(i);
			if (skin.getTipoOcorrencia().equals(tipoPendencia.getTipoOcorrencia())) {
				filtradasGrupoPendencia.add(skin);
			}
		}
		Collections.sort(filtradasGrupoPendencia);
		return filtradasGrupoPendencia;
	}
	
	public void refreshGrupoPendencia() {
		tipoPendencia.setSubGrupoPendencia(null);
		filtradasSubGrupoPendencia = new ArrayList<>();
	}

	public boolean isAutoCompletSubGrupoPendenciaHabilitado() {
		autoCompleteSubGrupoPendencia("");
		return filtradasSubGrupoPendencia.isEmpty();
	}
	public List<SubGrupoPendencia> autoCompleteSubGrupoPendencia(String query) {
		if (tipoPendencia.getGrupoPendencia() == null) {
			return new ArrayList<SubGrupoPendencia>();
		}
		List<SubGrupoPendencia> todas = subGrupoPendenciaService.findByName(query);
		filtradasSubGrupoPendencia = new ArrayList<>();

		for (int i = 0; i < todas.size(); i++) {
			SubGrupoPendencia skin = todas.get(i);
			if (skin.getGrupoPendencia().equals(tipoPendencia.getGrupoPendencia())) {
				filtradasSubGrupoPendencia.add(skin);
			}
		}
		Collections.sort(filtradasSubGrupoPendencia);
		return filtradasSubGrupoPendencia;
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}

}
