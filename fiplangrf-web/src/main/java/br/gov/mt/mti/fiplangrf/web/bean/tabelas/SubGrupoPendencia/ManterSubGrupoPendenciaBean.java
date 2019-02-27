package br.gov.mt.mti.fiplangrf.web.bean.tabelas.SubGrupoPendencia;

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
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.SubGrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterSubGrupoPendenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirSubGrupoPendencia", pattern = "/subgrupopendencia/incluir", viewId = "/pages/tabelas/subGrupoPendencia/manterSubGrupoPendencia.jsf"),
		@URLMapping(id = "alterarSubGrupoPendencia", pattern = "/subgrupopendencia/alterar/#{id:manterSubGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/subGrupoPendencia/manterSubGrupoPendencia.jsf"),
		@URLMapping(id = "visualizarSubGrupoPendencia", pattern = "/subgrupopendencia/visualizar/#{id:manterSubGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/subGrupoPendencia/manterSubGrupoPendencia.jsf"),
		@URLMapping(id = "excluirSubGrupoPendencia", pattern = "/subgrupopendencia/excluir/#{id:manterSubGrupoPendenciaBean.idCriptogradado}", viewId = "/pages/tabelas/subGrupoPendencia/manterSubGrupoPendencia.jsf")})
public class ManterSubGrupoPendenciaBean extends AbstractManterBean {

	private static final long serialVersionUID = -585237491437766368L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_SUBGRUPOPENDENCIA_VIEW = "pretty:alterarSubGrupoPendencia";

	public static final String VISUALIZAR_SUBGRUPOPENDENCIA_VIEW = "pretty:visualizarSubGrupoPendencia";

	public static final String EXCLUIR_SUBGRUPOPENDENCIA_VIEW = "pretty:excluirSubGrupoPendencia";

	public static final String PERMISSAO_INCLUIR_SUBGRUPOPENDENCIA = "incluir.subGrupoPendencia";

	public static final String PERMISSAO_ALTERAR_SUBGRUPOPENDENCIA = "alterar.subGrupoPendencia";

	public static final String PERMISSAO_EXCLUIR_SUBGRUPOPENDENCIA = "excluir.subGrupoPendencia";

	@Getter @Setter
	private Long id;

	@Getter @Setter
	private SubGrupoPendencia subGrupoPendencia;

	@Inject
	private GrupoPendenciaService grupoPendenciaService;

	@Inject
	private SubGrupoPendenciaService subGrupoPendenciaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		subGrupoPendencia = new SubGrupoPendencia();
	}

	@URLAction(mappingId = "incluirSubGrupoPendencia", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_SUBGRUPOPENDENCIA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarSubGrupoPendencia", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_SUBGRUPOPENDENCIA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarSubGrupoPendencia", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirSubGrupoPendencia", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_SUBGRUPOPENDENCIA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando SubGrupoPendencia: {}", getId());
		subGrupoPendencia = subGrupoPendenciaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = subGrupoPendencia.getId() == null;

		LOGGER.debug("Persistindo SubGrupoPendencia: {}", subGrupoPendencia);
		
		try {
			subGrupoPendencia = subGrupoPendenciaService.checkAndSave(subGrupoPendencia);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		setId(subGrupoPendencia.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo SubGrupoPendencia: {}", getId());
		subGrupoPendenciaService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}
	
	/*
	 * Métodos para Auto Complete
	 */
	public List<GrupoPendencia> autoCompleteGrupoPendencia(String query) {
		List<GrupoPendencia> filtradas = grupoPendenciaService.findByName(query);
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
