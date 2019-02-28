package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.NLA;
import br.gov.mt.mti.fiplangrf.service.tabelas.NLAService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterNLABean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirNLA", pattern = "/nla/incluir", viewId = "/pages/tabelas/nLA/manterNLA.jsf"),
		@URLMapping(id = "alterarNLA", pattern = "/nla/alterar/#{id:manterNLABean.idCriptogradado}", viewId = "/pages/tabelas/nLA/manterNLA.jsf"),
		@URLMapping(id = "visualizarNLA", pattern = "/nla/visualizar/#{id:manterNLABean.idCriptogradado}", viewId = "/pages/tabelas/nLA/manterNLA.jsf"),
		@URLMapping(id = "excluirNLA", pattern = "/nla/excluir/#{id:manterNLABean.idCriptogradado}", viewId = "/pages/tabelas/nLA/manterNLA.jsf")})
public class ManterNLABean extends AbstractManterBean {

	private static final long serialVersionUID = -166471252342602998L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_NLA_VIEW = "pretty:alterarNLA";

	public static final String VISUALIZAR_NLA_VIEW = "pretty:visualizarNLA";

	public static final String EXCLUIR_NLA_VIEW = "pretty:excluirNLA";

	public static final String PERMISSAO_INCLUIR_NLA = "incluir.nLA";

	public static final String PERMISSAO_ALTERAR_NLA = "alterar.nLA";

	public static final String PERMISSAO_EXCLUIR_NLA = "excluir.nLA";

	private Long id;

	private NLA nLA;

	@Inject
	private NLAService nLAService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		nLA = new NLA();
	}

	@URLAction(mappingId = "incluirNLA", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_NLA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarNLA", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_NLA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarNLA", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirNLA", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_NLA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando NLA: {}", getId());
		nLA = nLAService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = nLA.getId() == null;
		LOGGER.debug("Persistindo NLA: {}", nLA);
		nLAService.saveOrUpdate(nLA);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo NLA: {}", getId());
		nLAService.delete(getId());
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

	public NLA getNLA() {
		return nLA;
	}

	public void setNLA(NLA nLA) {
		this.nLA = nLA;
	}

}
