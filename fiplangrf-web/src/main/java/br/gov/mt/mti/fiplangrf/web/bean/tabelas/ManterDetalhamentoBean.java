package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import java.util.List;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalhamentoService;
import br.gov.mt.mti.fiplangrf.service.tabelas.RecursoFinanceiroService;
import br.gov.mt.mti.fiplangrf.service.tabelas.FonteRecursoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;

@Named("manterDetalhamentoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDetalhamento", pattern = "/detalhamento/incluir", viewId = "/pages/tabelas/detalhamento/manterDetalhamento.jsf"),
		@URLMapping(id = "alterarDetalhamento", pattern = "/detalhamento/alterar/#{id:manterDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamento/manterDetalhamento.jsf"),
		@URLMapping(id = "visualizarDetalhamento", pattern = "/detalhamento/visualizar/#{id:manterDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamento/manterDetalhamento.jsf"),
		@URLMapping(id = "excluirDetalhamento", pattern = "/detalhamento/excluir/#{id:manterDetalhamentoBean.idCriptogradado}", viewId = "/pages/tabelas/detalhamento/manterDetalhamento.jsf")})
public class ManterDetalhamentoBean extends AbstractManterBean {

	private static final long serialVersionUID = 353716567712533400L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DETALHAMENTO_VIEW = "pretty:alterarDetalhamento";

	public static final String VISUALIZAR_DETALHAMENTO_VIEW = "pretty:visualizarDetalhamento";

	public static final String EXCLUIR_DETALHAMENTO_VIEW = "pretty:excluirDetalhamento";

	public static final String PERMISSAO_INCLUIR_DETALHAMENTO = "incluir.detalhamento";

	public static final String PERMISSAO_ALTERAR_DETALHAMENTO = "alterar.detalhamento";

	public static final String PERMISSAO_EXCLUIR_DETALHAMENTO = "excluir.detalhamento";

	private Long id;

	private Detalhamento detalhamento;

	private List<RecursoFinanceiro> listaRecursoFinanceiro;

	private List<FonteRecurso> listaFonteRecurso;

	@Inject
	private RecursoFinanceiroService recursoFinanceiroService;

	@Inject
	private FonteRecursoService fonteRecursoService;

	@Inject
	private DetalhamentoService detalhamentoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		detalhamento = new Detalhamento();
		LOGGER.debug("Carregando lista de RecursoFinanceiro");
		listaRecursoFinanceiro = recursoFinanceiroService.findAll();
		LOGGER.debug("Carregando lista de FonteRecurso");
		listaFonteRecurso = fonteRecursoService.findAll();
	}

	@URLAction(mappingId = "incluirDetalhamento", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DETALHAMENTO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDetalhamento", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DETALHAMENTO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDetalhamento", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDetalhamento", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DETALHAMENTO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando Detalhamento: {}", getId());
		detalhamento = detalhamentoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = detalhamento.getId() == null;
		LOGGER.debug("Persistindo Detalhamento: {}", detalhamento);
		detalhamentoService.saveOrUpdate(detalhamento);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo Detalhamento: {}", getId());
		detalhamentoService.delete(getId());
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

	public Detalhamento getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(Detalhamento detalhamento) {
		this.detalhamento = detalhamento;
	}

	public List<RecursoFinanceiro> getListaRecursoFinanceiro() {
		return listaRecursoFinanceiro;
	}

	public void setListaRecursoFinanceiro(List<RecursoFinanceiro> listaRecursoFinanceiro) {
		this.listaRecursoFinanceiro = listaRecursoFinanceiro;
	}

	public List<FonteRecurso> getListaFonteRecurso() {
		return listaFonteRecurso;
	}

	public void setListaFonteRecurso(List<FonteRecurso> listaFonteRecurso) {
		this.listaFonteRecurso = listaFonteRecurso;
	}

}
