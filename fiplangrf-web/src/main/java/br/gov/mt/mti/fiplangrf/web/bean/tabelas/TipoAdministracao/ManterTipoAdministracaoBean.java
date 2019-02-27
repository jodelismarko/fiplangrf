package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoAdministracao;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoAdministracaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterTipoAdministracaoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirTipoAdministracao", pattern = "/tipoadministracao/incluir", viewId = "/pages/tabelas/tipoAdministracao/manterTipoAdministracao.jsf"),
		@URLMapping(id = "alterarTipoAdministracao", pattern = "/tipoadministracao/alterar/#{id:manterTipoAdministracaoBean.idCriptogradado}", viewId = "/pages/tabelas/tipoAdministracao/manterTipoAdministracao.jsf"),
		@URLMapping(id = "visualizarTipoAdministracao", pattern = "/tipoadministracao/visualizar/#{id:manterTipoAdministracaoBean.idCriptogradado}", viewId = "/pages/tabelas/tipoAdministracao/manterTipoAdministracao.jsf"),
		@URLMapping(id = "excluirTipoAdministracao", pattern = "/tipoadministracao/excluir/#{id:manterTipoAdministracaoBean.idCriptogradado}", viewId = "/pages/tabelas/tipoAdministracao/manterTipoAdministracao.jsf")})
public class ManterTipoAdministracaoBean extends AbstractManterBean {

	private static final long serialVersionUID = 180884304192721966L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_TIPOADMINISTRACAO_VIEW = "pretty:alterarTipoAdministracao";

	public static final String VISUALIZAR_TIPOADMINISTRACAO_VIEW = "pretty:visualizarTipoAdministracao";

	public static final String EXCLUIR_TIPOADMINISTRACAO_VIEW = "pretty:excluirTipoAdministracao";

	public static final String PERMISSAO_INCLUIR_TIPOADMINISTRACAO = "incluir.tipoAdministracao";

	public static final String PERMISSAO_ALTERAR_TIPOADMINISTRACAO = "alterar.tipoAdministracao";

	public static final String PERMISSAO_EXCLUIR_TIPOADMINISTRACAO = "excluir.tipoAdministracao";

	@Getter @Setter
	private Long id;

	@Getter @Setter
	private TipoAdministracao tipoAdministracao;

	@Inject
	private TipoAdministracaoService tipoAdministracaoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		tipoAdministracao = new TipoAdministracao();
	}

	@URLAction(mappingId = "incluirTipoAdministracao", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_TIPOADMINISTRACAO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarTipoAdministracao", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_TIPOADMINISTRACAO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarTipoAdministracao", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirTipoAdministracao", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_TIPOADMINISTRACAO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando TipoAdministracao: {}", getId());
		tipoAdministracao = tipoAdministracaoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = tipoAdministracao.getId() == null;

		LOGGER.debug("Persistindo tipoAdministracao: {}", tipoAdministracao);
		
		try {
			tipoAdministracao = tipoAdministracaoService.checkAndSave(tipoAdministracao);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		setId(tipoAdministracao.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo TipoAdministracao: {}", getId());
		tipoAdministracaoService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}
}
