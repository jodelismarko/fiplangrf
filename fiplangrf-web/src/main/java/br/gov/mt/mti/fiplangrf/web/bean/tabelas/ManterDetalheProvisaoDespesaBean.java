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
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.service.tabelas.DetalheProvisaoDespesaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("manterDetalheProvisaoDespesaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirDetalheProvisaoDespesa", pattern = "/detalheprovisaodespesa/incluir", viewId = "/pages/tabelas/detalheProvisaoDespesa/manterDetalheProvisaoDespesa.jsf"),
		@URLMapping(id = "alterarDetalheProvisaoDespesa", pattern = "/detalheprovisaodespesa/alterar/#{id:manterDetalheProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheProvisaoDespesa/manterDetalheProvisaoDespesa.jsf"),
		@URLMapping(id = "visualizarDetalheProvisaoDespesa", pattern = "/detalheprovisaodespesa/visualizar/#{id:manterDetalheProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheProvisaoDespesa/manterDetalheProvisaoDespesa.jsf"),
		@URLMapping(id = "excluirDetalheProvisaoDespesa", pattern = "/detalheprovisaodespesa/excluir/#{id:manterDetalheProvisaoDespesaBean.idCriptogradado}", viewId = "/pages/tabelas/detalheProvisaoDespesa/manterDetalheProvisaoDespesa.jsf")})
public class ManterDetalheProvisaoDespesaBean extends AbstractManterBean {

	private static final long serialVersionUID = -324875275035379096L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_DETALHEPROVISAODESPESA_VIEW = "pretty:alterarDetalheProvisaoDespesa";

	public static final String VISUALIZAR_DETALHEPROVISAODESPESA_VIEW = "pretty:visualizarDetalheProvisaoDespesa";

	public static final String EXCLUIR_DETALHEPROVISAODESPESA_VIEW = "pretty:excluirDetalheProvisaoDespesa";

	public static final String PERMISSAO_INCLUIR_DETALHEPROVISAODESPESA = "incluir.detalheProvisaoDespesa";

	public static final String PERMISSAO_ALTERAR_DETALHEPROVISAODESPESA = "alterar.detalheProvisaoDespesa";

	public static final String PERMISSAO_EXCLUIR_DETALHEPROVISAODESPESA = "excluir.detalheProvisaoDespesa";

	private Long id;

	private DetalheProvisaoDespesa detalheProvisaoDespesa;

	@Inject
	private DetalheProvisaoDespesaService detalheProvisaoDespesaService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		detalheProvisaoDespesa = new DetalheProvisaoDespesa();
	}

	@URLAction(mappingId = "incluirDetalheProvisaoDespesa", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_DETALHEPROVISAODESPESA);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "alterarDetalheProvisaoDespesa", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_DETALHEPROVISAODESPESA);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "visualizarDetalheProvisaoDespesa", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirDetalheProvisaoDespesa", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_DETALHEPROVISAODESPESA);
		super.setModoExclusao();
		carregar();
	}

	public void carregar() {
		LOGGER.debug("Carregando DetalheProvisaoDespesa: {}", getId());
		detalheProvisaoDespesa = detalheProvisaoDespesaService.findByIdFetchAll(getId());
	}

	public void salvar() throws BusinessException {
		LOGGER.debug("Persistindo DetalheProvisaoDespesa: {}", detalheProvisaoDespesa);
		detalheProvisaoDespesaService.saveOrUpdate(detalheProvisaoDespesa);
		if (detalheProvisaoDespesa.isNew()) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo DetalheProvisaoDespesa: {}", getId());
		detalheProvisaoDespesaService.delete(getId());
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

	public DetalheProvisaoDespesa getDetalheProvisaoDespesa() {
		return detalheProvisaoDespesa;
	}

	public void setDetalheProvisaoDespesa(DetalheProvisaoDespesa detalheProvisaoDespesa) {
		this.detalheProvisaoDespesa = detalheProvisaoDespesa;
	}

}
