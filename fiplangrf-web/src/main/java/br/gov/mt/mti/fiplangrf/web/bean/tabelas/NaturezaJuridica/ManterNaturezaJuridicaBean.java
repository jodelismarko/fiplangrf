package br.gov.mt.mti.fiplangrf.web.bean.tabelas.NaturezaJuridica;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import br.gov.mt.mti.fiplangrf.service.tabelas.NaturezaJuridicaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterNaturezaJuridicaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirNaturezaJuridica", pattern = "/naturezajuridica/incluir", viewId = "/pages/tabelas/naturezaJuridica/manterNaturezaJuridica.jsf"),
		@URLMapping(id = "alterarNaturezaJuridica", pattern = "/naturezajuridica/alterar/#{id:manterNaturezaJuridicaBean.idCriptogradado}", viewId = "/pages/tabelas/naturezaJuridica/manterNaturezaJuridica.jsf"),
		@URLMapping(id = "visualizarNaturezaJuridica", pattern = "/naturezajuridica/visualizar/#{id:manterNaturezaJuridicaBean.idCriptogradado}", viewId = "/pages/tabelas/naturezaJuridica/manterNaturezaJuridica.jsf"),
		@URLMapping(id = "excluirNaturezaJuridica", pattern = "/naturezajuridica/excluir/#{id:manterNaturezaJuridicaBean.idCriptogradado}", viewId = "/pages/tabelas/naturezaJuridica/manterNaturezaJuridica.jsf")})
public class ManterNaturezaJuridicaBean extends AbstractManterBean {

	private static final long serialVersionUID = 836296506201469649L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_NATUREZAJURIDICA_VIEW = "pretty:alterarNaturezaJuridica";

	public static final String VISUALIZAR_NATUREZAJURIDICA_VIEW = "pretty:visualizarNaturezaJuridica";

	public static final String EXCLUIR_NATUREZAJURIDICA_VIEW = "pretty:excluirNaturezaJuridica";

	public static final String PERMISSAO_INCLUIR_NATUREZAJURIDICA = "incluir.naturezaJuridica";

	public static final String PERMISSAO_ALTERAR_NATUREZAJURIDICA = "alterar.naturezaJuridica";

	public static final String PERMISSAO_EXCLUIR_NATUREZAJURIDICA = "excluir.naturezaJuridica";

	private Long id;

	private NaturezaJuridica naturezaJuridica;

	@Inject
	private NaturezaJuridicaService naturezaJuridicaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		naturezaJuridica = new NaturezaJuridica();
	}

	@URLAction(mappingId = "incluirNaturezaJuridica", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_NATUREZAJURIDICA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarNaturezaJuridica", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_NATUREZAJURIDICA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarNaturezaJuridica", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirNaturezaJuridica", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_NATUREZAJURIDICA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando NaturezaJuridica: {}", getId());
		naturezaJuridica = naturezaJuridicaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = naturezaJuridica.getId() == null;

		LOGGER.debug("Persistindo Natureza Juridica: {}", naturezaJuridica);
		
		try {
			naturezaJuridica = naturezaJuridicaService.checkAndSave(naturezaJuridica);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch(BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		setId(naturezaJuridica.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo NaturezaJuridica: {}", getId());
		naturezaJuridicaService.delete(getId());
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

	public NaturezaJuridica getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(NaturezaJuridica naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

}
