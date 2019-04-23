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
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;
import br.gov.mt.mti.fiplangrf.service.tabelas.PlanejamentoAnualPrazosService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterPlanejamentoAnualPrazosBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirPlanejamentoAnualPrazos", pattern = "/planejamentoanualprazos/incluir", viewId = "/pages/tabelas/planejamentoAnualPrazos/manterPlanejamentoAnualPrazos.jsf"),
		@URLMapping(id = "alterarPlanejamentoAnualPrazos", pattern = "/planejamentoanualprazos/alterar/#{id:manterPlanejamentoAnualPrazosBean.idCriptogradado}", viewId = "/pages/tabelas/planejamentoAnualPrazos/manterPlanejamentoAnualPrazos.jsf"),
		@URLMapping(id = "visualizarPlanejamentoAnualPrazos", pattern = "/planejamentoanualprazos/visualizar/#{id:manterPlanejamentoAnualPrazosBean.idCriptogradado}", viewId = "/pages/tabelas/planejamentoAnualPrazos/manterPlanejamentoAnualPrazos.jsf"),
		@URLMapping(id = "excluirPlanejamentoAnualPrazos", pattern = "/planejamentoanualprazos/excluir/#{id:manterPlanejamentoAnualPrazosBean.idCriptogradado}", viewId = "/pages/tabelas/planejamentoAnualPrazos/manterPlanejamentoAnualPrazos.jsf")})
public class ManterPlanejamentoAnualPrazosBean extends AbstractManterBean {

	private static final long serialVersionUID = -19215103802315336L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_PLANEJAMENTOANUALPRAZOS_VIEW = "pretty:alterarPlanejamentoAnualPrazos";

	public static final String VISUALIZAR_PLANEJAMENTOANUALPRAZOS_VIEW = "pretty:visualizarPlanejamentoAnualPrazos";

	public static final String EXCLUIR_PLANEJAMENTOANUALPRAZOS_VIEW = "pretty:excluirPlanejamentoAnualPrazos";

	public static final String PERMISSAO_INCLUIR_PLANEJAMENTOANUALPRAZOS = "incluir.planejamentoAnualPrazos";

	public static final String PERMISSAO_ALTERAR_PLANEJAMENTOANUALPRAZOS = "alterar.planejamentoAnualPrazos";

	public static final String PERMISSAO_EXCLUIR_PLANEJAMENTOANUALPRAZOS = "excluir.planejamentoAnualPrazos";

	private Long id;

	private PlanejamentoAnualPrazos planejamentoAnualPrazos;

	@Inject
	private PlanejamentoAnualPrazosService planejamentoAnualPrazosService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		planejamentoAnualPrazos = new PlanejamentoAnualPrazos();
	}

	@URLAction(mappingId = "incluirPlanejamentoAnualPrazos", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_PLANEJAMENTOANUALPRAZOS);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarPlanejamentoAnualPrazos", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_PLANEJAMENTOANUALPRAZOS);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarPlanejamentoAnualPrazos", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirPlanejamentoAnualPrazos", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_PLANEJAMENTOANUALPRAZOS);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando PlanejamentoAnualPrazos: {}", getId());
		planejamentoAnualPrazos = planejamentoAnualPrazosService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo PlanejamentoAnualPrazos: {}", planejamentoAnualPrazos);
		planejamentoAnualPrazosService.saveOrUpdate(planejamentoAnualPrazos);
		if (planejamentoAnualPrazos.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo PlanejamentoAnualPrazos: {}", getId());
		planejamentoAnualPrazosService.delete(getId());
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

	public PlanejamentoAnualPrazos getPlanejamentoAnualPrazos() {
		return planejamentoAnualPrazos;
	}

	public void setPlanejamentoAnualPrazos(PlanejamentoAnualPrazos planejamentoAnualPrazos) {
		this.planejamentoAnualPrazos = planejamentoAnualPrazos;
	}

}
