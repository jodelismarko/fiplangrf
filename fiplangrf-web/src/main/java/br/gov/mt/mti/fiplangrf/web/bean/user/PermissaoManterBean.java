package br.gov.mt.mti.fiplangrf.web.bean.user;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.user.PermissaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("permissaoManterBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirPermissao", pattern = "/dev/permissao/incluir", viewId = "/pages/user/permissao/permissaoManter.jsf"),
		@URLMapping(id = "alterarPermissao", pattern = "/dev/permissao/alterar/#{ id : permissaoManterBean.id}", viewId = "/pages/user/permissao/permissaoManter.jsf"),
		@URLMapping(id = "visualizarPermissao", pattern = "/dev/permissao/visualizar/#{ id : permissaoManterBean.id}", viewId = "/pages/user/permissao/permissaoManter.jsf"),
		@URLMapping(id = "excluirPermissao", pattern = "/dev/permissao/excluir/#{ id : permissaoManterBean.id}", viewId = "/pages/user/permissao/permissaoManter.jsf") })
public class PermissaoManterBean extends AbstractManterBean {

	private static final long serialVersionUID = 169563520499046485L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_PERMISSAO_VIEW = "pretty:alterarPermissao";

	public static final String VISUALIZAR_PERMISSAO_VIEW = "pretty:visualizarPermissao";

	public static final String EXCLUIR_PERMISSAO_VIEW = "pretty:excluirPermissao";

	private Long id;

	private Permissao permissao;

	@Inject
	private PermissaoService permissaoService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		setId(null);
		permissao = new Permissao();
	}

	public void carregar() {
		LOGGER.debug("Carregando Permissao: {}", getId());
		permissao = permissaoService.findByIdFetchAll(getId());
	}

	@URLAction(mappingId = "alterarPermissao", onPostback = false)
	public void configurarModoAlteracao() {
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "incluirPermissao", onPostback = false)
	public void configurarModoInclusao() {
		super.setModoInclusao();
	}

	@URLAction(mappingId = "visualizarPermissao", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirPermissao", onPostback = false)
	public void configurarModoExclusao() {
		super.setModoExclusao();
		carregar();

	}

	public void salvar() throws BusinessException {

		boolean inclusao = permissao.getId() == null;

		LOGGER.debug("Persistindo Permissao: {}", permissao);

		try {
			permissaoService.checkAndSave(permissao);
			
			if (inclusao) {
				addInfoMessage(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
			} else {
				addInfoMessage(BeanMessageConstants.MSG_ALTERAR_SUCESSO);
			}
		} catch (BusinessException e) {
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		
		setId(permissao.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo Permissao: {}", id);
		permissaoService.delete(id);
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}
