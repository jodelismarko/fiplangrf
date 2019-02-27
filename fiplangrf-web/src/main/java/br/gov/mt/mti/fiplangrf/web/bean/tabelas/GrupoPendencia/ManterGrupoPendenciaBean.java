package br.gov.mt.mti.fiplangrf.web.bean.tabelas.GrupoPendencia;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Named("manterGrupoPendenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirGrupoPendencia", pattern = "/grupopendencia/incluir", viewId = "/pages/tabelas/grupoPendencia/manterGrupoPendencia.jsf"),
		@URLMapping(id = "alterarGrupoPendencia", pattern = "/grupopendencia/alterar/#{id:manterGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoPendencia/manterGrupoPendencia.jsf"),
		@URLMapping(id = "visualizarGrupoPendencia", pattern = "/grupopendencia/visualizar/#{id:manterGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoPendencia/manterGrupoPendencia.jsf"),
		@URLMapping(id = "excluirGrupoPendencia", pattern = "/grupopendencia/excluir/#{id:manterGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/grupoPendencia/manterGrupoPendencia.jsf")})
public class ManterGrupoPendenciaBean extends AbstractManterBean {

	private static final long serialVersionUID = -99184916541598399L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_GRUPOPENDENCIA_VIEW = "pretty:alterarGrupoPendencia";

	public static final String VISUALIZAR_GRUPOPENDENCIA_VIEW = "pretty:visualizarGrupoPendencia";

	public static final String EXCLUIR_GRUPOPENDENCIA_VIEW = "pretty:excluirGrupoPendencia";

	public static final String PERMISSAO_INCLUIR_GRUPOPENDENCIA = "incluir.grupoPendencia";

	public static final String PERMISSAO_ALTERAR_GRUPOPENDENCIA = "alterar.grupoPendencia";

	public static final String PERMISSAO_EXCLUIR_GRUPOPENDENCIA = "excluir.grupoPendencia";

	@Getter @Setter
	private Long id;

	@Getter @Setter
	private GrupoPendencia grupoPendencia;

	@Inject
	private GrupoPendenciaService grupoPendenciaService;
	
	@Inject
	private TipoOcorrenciaService tipoOcorrenciaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		grupoPendencia = new GrupoPendencia();
	}

	@URLAction(mappingId = "incluirGrupoPendencia", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_GRUPOPENDENCIA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarGrupoPendencia", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_GRUPOPENDENCIA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarGrupoPendencia", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirGrupoPendencia", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_GRUPOPENDENCIA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando GrupoPendencia: {}", getId());
		grupoPendencia = grupoPendenciaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = grupoPendencia.getId() == null;

		LOGGER.debug("Persistindo Grupo: {}", grupoPendencia);
		
		try {
			grupoPendencia = grupoPendenciaService.checkAndSave(grupoPendencia);
			if (inclusao) {
				
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		setId(grupoPendencia.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo GrupoPendencia: {}", getId());
		grupoPendenciaService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}
	
	/*
	 * Métodos para Auto Complete
	 */
	public List<TipoOcorrencia> autoCompleteTipoOcorrencia(String query) {
		List<TipoOcorrencia> filtradas = tipoOcorrenciaService.findByName(query);
		Collections.sort(filtradas);
		return filtradas;
	}
	
	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}

}
