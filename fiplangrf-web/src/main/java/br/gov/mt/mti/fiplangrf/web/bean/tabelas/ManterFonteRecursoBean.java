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
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;
import br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterFonteRecursoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirFonteRecurso", pattern = "/fonterecurso/incluir", viewId = "/pages/tabelas/fonteRecurso/manterFonteRecurso.jsf"),
		@URLMapping(id = "alterarFonteRecurso", pattern = "/fonterecurso/alterar/#{id:manterFonteRecursoBean.idCriptogradado}", viewId = "/pages/tabelas/fonteRecurso/manterFonteRecurso.jsf"),
		@URLMapping(id = "visualizarFonteRecurso", pattern = "/fonterecurso/visualizar/#{id:manterFonteRecursoBean.idCriptogradado}", viewId = "/pages/tabelas/fonteRecurso/manterFonteRecurso.jsf"),
		@URLMapping(id = "excluirFonteRecurso", pattern = "/fonterecurso/excluir/#{id:manterFonteRecursoBean.idCriptogradado}", viewId = "/pages/tabelas/fonteRecurso/manterFonteRecurso.jsf")})
public class ManterFonteRecursoBean extends AbstractManterBean {

	private static final long serialVersionUID = -277283491614166827L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_FONTERECURSO_VIEW = "pretty:alterarFonteRecurso";

	public static final String VISUALIZAR_FONTERECURSO_VIEW = "pretty:visualizarFonteRecurso";

	public static final String EXCLUIR_FONTERECURSO_VIEW = "pretty:excluirFonteRecurso";

	public static final String PERMISSAO_INCLUIR_FONTERECURSO = "incluir.fonteRecurso";

	public static final String PERMISSAO_ALTERAR_FONTERECURSO = "alterar.fonteRecurso";

	public static final String PERMISSAO_EXCLUIR_FONTERECURSO = "excluir.fonteRecurso";

	private Long id;

	private FonteRecurso fonteRecurso;

	@Inject
	private FonteRecursoService fonteRecursoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		fonteRecurso = new FonteRecurso();
	}

	@URLAction(mappingId = "incluirFonteRecurso", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_FONTERECURSO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarFonteRecurso", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_FONTERECURSO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarFonteRecurso", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirFonteRecurso", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_FONTERECURSO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando FonteRecurso: {}", getId());
		fonteRecurso = fonteRecursoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo FonteRecurso: {}", fonteRecurso);
		fonteRecursoService.saveOrUpdate(fonteRecurso);
		if (fonteRecurso.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo FonteRecurso: {}", getId());
		fonteRecursoService.delete(getId());
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

	public FonteRecurso getFonteRecurso() {
		return fonteRecurso;
	}

	public void setFonteRecurso(FonteRecurso fonteRecurso) {
		this.fonteRecurso = fonteRecurso;
	}

}
