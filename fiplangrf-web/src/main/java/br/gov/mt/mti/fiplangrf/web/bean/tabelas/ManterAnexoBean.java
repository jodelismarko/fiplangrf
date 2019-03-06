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
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.service.tabelas.AnexoService;
import br.gov.mt.mti.fiplangrf.service.tabelas.ArquivoAnexoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

@Named("manterAnexoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirAnexo", pattern = "/anexo/incluir", viewId = "/pages/tabelas/anexo/manterAnexo.jsf"),
		@URLMapping(id = "alterarAnexo", pattern = "/anexo/alterar/#{id:manterAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/anexo/manterAnexo.jsf"),
		@URLMapping(id = "visualizarAnexo", pattern = "/anexo/visualizar/#{id:manterAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/anexo/manterAnexo.jsf"),
		@URLMapping(id = "excluirAnexo", pattern = "/anexo/excluir/#{id:manterAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/anexo/manterAnexo.jsf")})
public class ManterAnexoBean extends AbstractManterBean {

	private static final long serialVersionUID = -596803624640530296L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_ANEXO_VIEW = "pretty:alterarAnexo";

	public static final String VISUALIZAR_ANEXO_VIEW = "pretty:visualizarAnexo";

	public static final String EXCLUIR_ANEXO_VIEW = "pretty:excluirAnexo";

	public static final String PERMISSAO_INCLUIR_ANEXO = "incluir.anexo";

	public static final String PERMISSAO_ALTERAR_ANEXO = "alterar.anexo";

	public static final String PERMISSAO_EXCLUIR_ANEXO = "excluir.anexo";

	private Long id;

	private Anexo anexo;

	private List<ArquivoAnexo> listaArquivoAnexo;

	@Inject
	private ArquivoAnexoService arquivoAnexoService;

	@Inject
	private AnexoService anexoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		anexo = new Anexo();
		LOGGER.debug("Carregando lista de ArquivoAnexo");
		listaArquivoAnexo = arquivoAnexoService.findAll();
	}

	@URLAction(mappingId = "incluirAnexo", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_ANEXO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarAnexo", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_ANEXO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarAnexo", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirAnexo", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_ANEXO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando Anexo: {}", getId());
		anexo = anexoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = anexo.getId() == null;
		LOGGER.debug("Persistindo Anexo: {}", anexo);
		anexoService.saveOrUpdate(anexo);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo Anexo: {}", getId());
		anexoService.delete(getId());
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

	public Anexo getAnexo() {
		return anexo;
	}

	public void setAnexo(Anexo anexo) {
		this.anexo = anexo;
	}

	public List<ArquivoAnexo> getListaArquivoAnexo() {
		return listaArquivoAnexo;
	}

	public void setListaArquivoAnexo(List<ArquivoAnexo> listaArquivoAnexo) {
		this.listaArquivoAnexo = listaArquivoAnexo;
	}

}
