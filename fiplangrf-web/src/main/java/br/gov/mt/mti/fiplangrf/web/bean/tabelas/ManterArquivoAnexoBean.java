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
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;
import br.gov.mt.mti.fiplangrf.service.tabelas.ArquivoAnexoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterArquivoAnexoBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirArquivoAnexo", pattern = "/arquivoanexo/incluir", viewId = "/pages/tabelas/arquivoAnexo/manterArquivoAnexo.jsf"),
		@URLMapping(id = "alterarArquivoAnexo", pattern = "/arquivoanexo/alterar/#{id:manterArquivoAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/arquivoAnexo/manterArquivoAnexo.jsf"),
		@URLMapping(id = "visualizarArquivoAnexo", pattern = "/arquivoanexo/visualizar/#{id:manterArquivoAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/arquivoAnexo/manterArquivoAnexo.jsf"),
		@URLMapping(id = "excluirArquivoAnexo", pattern = "/arquivoanexo/excluir/#{id:manterArquivoAnexoBean.idCriptogradado}", viewId = "/pages/tabelas/arquivoAnexo/manterArquivoAnexo.jsf")})
public class ManterArquivoAnexoBean extends AbstractManterBean {

	private static final long serialVersionUID = -485316673971900994L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_ARQUIVOANEXO_VIEW = "pretty:alterarArquivoAnexo";

	public static final String VISUALIZAR_ARQUIVOANEXO_VIEW = "pretty:visualizarArquivoAnexo";

	public static final String EXCLUIR_ARQUIVOANEXO_VIEW = "pretty:excluirArquivoAnexo";

	public static final String PERMISSAO_INCLUIR_ARQUIVOANEXO = "incluir.arquivoAnexo";

	public static final String PERMISSAO_ALTERAR_ARQUIVOANEXO = "alterar.arquivoAnexo";

	public static final String PERMISSAO_EXCLUIR_ARQUIVOANEXO = "excluir.arquivoAnexo";

	private Long id;

	private ArquivoAnexo arquivoAnexo;

	@Inject
	private ArquivoAnexoService arquivoAnexoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		arquivoAnexo = new ArquivoAnexo();
	}

	@URLAction(mappingId = "incluirArquivoAnexo", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_ARQUIVOANEXO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarArquivoAnexo", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_ARQUIVOANEXO);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarArquivoAnexo", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirArquivoAnexo", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_ARQUIVOANEXO);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando ArquivoAnexo: {}", getId());
		arquivoAnexo = arquivoAnexoService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		boolean inclusao = arquivoAnexo.getId() == null;
		LOGGER.debug("Persistindo ArquivoAnexo: {}", arquivoAnexo);
		arquivoAnexoService.saveOrUpdate(arquivoAnexo);
		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo ArquivoAnexo: {}", getId());
		arquivoAnexoService.delete(getId());
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

	public ArquivoAnexo getArquivoAnexo() {
		return arquivoAnexo;
	}

	public void setArquivoAnexo(ArquivoAnexo arquivoAnexo) {
		this.arquivoAnexo = arquivoAnexo;
	}

}
