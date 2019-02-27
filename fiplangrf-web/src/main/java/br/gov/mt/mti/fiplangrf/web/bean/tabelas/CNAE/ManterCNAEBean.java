package br.gov.mt.mti.fiplangrf.web.bean.tabelas.CNAE;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAE;
import br.gov.mt.mti.fiplangrf.service.tabelas.CNAEService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterCNAEBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirCNAE", pattern = "/cnae/incluir", viewId = "/pages/tabelas/cnae/manterCNAE.jsf"),
		@URLMapping(id = "alterarCNAE", pattern = "/cnae/alterar/#{id:manterCNAEBean.idCriptogradado}", viewId = "/pages/tabelas/cnae/manterCNAE.jsf"),
		@URLMapping(id = "visualizarCNAE", pattern = "/cnae/visualizar/#{id:manterCNAEBean.idCriptogradado}", viewId = "/pages/tabelas/cnae/manterCNAE.jsf"),
		@URLMapping(id = "excluirCNAE", pattern = "/cnae/excluir/#{id:manterCNAEBean.idCriptogradado}", viewId = "/pages/tabelas/cnae/manterCNAE.jsf") })
public class ManterCNAEBean extends AbstractManterBean {

	private static final long serialVersionUID = -654814377434046035L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_CNAE_VIEW = "pretty:alterarCNAE";

	public static final String VISUALIZAR_CNAE_VIEW = "pretty:visualizarCNAE";

	public static final String EXCLUIR_CNAE_VIEW = "pretty:excluirCNAE";

	public static final String PERMISSAO_INCLUIR_CNAE = "incluir.cnae";

	public static final String PERMISSAO_ALTERAR_CNAE = "alterar.cnae";

	public static final String PERMISSAO_EXCLUIR_CNAE = "excluir.cnae";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private CNAE cnae;

	@Inject
	private CNAEService cnaeService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		cnae = new CNAE();
	}

	@URLAction(mappingId = "incluirCNAE", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_CNAE);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarCNAE", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_CNAE);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarCNAE", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirCNAE", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_CNAE);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando CNAE: {}", getId());
		cnae = cnaeService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = cnae.getId() == null;

		LOGGER.debug("Persistindo CNAE: {}", cnae);

		try {
			setCnae(cnaeService.checkAndSave(cnae));
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		setId(cnae.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo CNAE: {}", getId());
		cnaeService.delete(getId());
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}

}
