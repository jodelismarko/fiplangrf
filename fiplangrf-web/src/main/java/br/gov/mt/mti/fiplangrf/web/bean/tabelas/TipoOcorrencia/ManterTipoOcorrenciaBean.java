package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoOcorrencia;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterTipoOcorrenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirTipoOcorrencia", pattern = "/tipoocorrencia/incluir", viewId = "/pages/tabelas/tipoOcorrencia/manterTipoOcorrencia.jsf"),
		@URLMapping(id = "alterarTipoOcorrencia", pattern = "/tipoocorrencia/alterar/#{id:manterTipoOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoOcorrencia/manterTipoOcorrencia.jsf"),
		@URLMapping(id = "visualizarTipoOcorrencia", pattern = "/tipoocorrencia/visualizar/#{id:manterTipoOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoOcorrencia/manterTipoOcorrencia.jsf"),
		@URLMapping(id = "excluirTipoOcorrencia", pattern = "/tipoocorrencia/excluir/#{id:manterTipoOcorrenciaBean.idCriptogradado}", viewId = "/pages/tabelas/tipoOcorrencia/manterTipoOcorrencia.jsf") })
public class ManterTipoOcorrenciaBean extends AbstractManterBean {

	private static final long serialVersionUID = -79694588588726025L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_TIPOOCORRENCIA_VIEW = "pretty:alterarTipoOcorrencia";

	public static final String VISUALIZAR_TIPOOCORRENCIA_VIEW = "pretty:visualizarTipoOcorrencia";

	public static final String EXCLUIR_TIPOOCORRENCIA_VIEW = "pretty:excluirTipoOcorrencia";

	public static final String PERMISSAO_INCLUIR_TIPOOCORRENCIA = "incluir.tipoOcorrencia";

	public static final String PERMISSAO_ALTERAR_TIPOOCORRENCIA = "alterar.tipoOcorrencia";

	public static final String PERMISSAO_EXCLUIR_TIPOOCORRENCIA = "excluir.tipoOcorrencia";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private TipoOcorrencia tipoOcorrencia;
	
	@Getter
	private List<TipoOcorrencia> listarTipoOCorrencia;

	@Inject
	private TipoOcorrenciaService tipoOcorrenciaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		tipoOcorrencia = new TipoOcorrencia();
	}

	@URLAction(mappingId = "incluirTipoOcorrencia", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_TIPOOCORRENCIA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarTipoOcorrencia", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_TIPOOCORRENCIA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarTipoOcorrencia", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirTipoOcorrencia", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_TIPOOCORRENCIA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando TipoOcorrencia: {}", getId());
		tipoOcorrencia = tipoOcorrenciaService.findByIdFetchAll(getId());
		listarTipoOCorrencia = tipoOcorrenciaService.findAll();
	}
	
	public void salvar() throws BusinessException {
		boolean inclusao = tipoOcorrencia.getId() == null;

		LOGGER.debug("Persistindo TipoOcorrencia: {}", tipoOcorrencia);

		try {
			tipoOcorrencia = tipoOcorrenciaService.checkAndSave(tipoOcorrencia);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		setId(tipoOcorrencia.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo TipoOcorrencia: {}", getId());
		tipoOcorrenciaService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}
}
