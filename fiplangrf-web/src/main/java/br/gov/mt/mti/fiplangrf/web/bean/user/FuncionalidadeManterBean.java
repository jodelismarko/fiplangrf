package br.gov.mt.mti.fiplangrf.web.bean.user;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Named("funcionalidadeManterBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirFuncionalidade", pattern = "/funcionalidade/incluir", viewId = "/pages/user/funcionalidade/funcionalidadeManter.jsf"),
		@URLMapping(id = "alterarFuncionalidade", pattern = "/funcionalidade/alterar/#{ id : funcionalidadeManterBean.id}", viewId = "/pages/user/funcionalidade/funcionalidadeManter.jsf"),
		@URLMapping(id = "visualizarFuncionalidade", pattern = "/funcionalidade/visualizar/#{ id : funcionalidadeManterBean.id}", viewId = "/pages/user/funcionalidade/funcionalidadeManter.jsf"),
		@URLMapping(id = "excluirFuncionalidade", pattern = "/funcionalidade/excluir/#{ id : funcionalidadeManterBean.id}", viewId = "/pages/user/funcionalidade/funcionalidadeManter.jsf") })
public class FuncionalidadeManterBean extends AbstractManterBean {

    private static final long serialVersionUID = 552220460822992618L;

	@Inject
    private Logger LOGGER;

	public static final String ALTERAR_FUNCIONALIDADE_VIEW = "pretty:alterarFuncionalidade";

	public static final String VISUALIZAR_FUNCIONALIDADE_VIEW = "pretty:visualizarFuncionalidade";

	public static final String EXCLUIR_FUNCIONALIDADE_VIEW = "pretty:excluirFuncionalidade";

	@Getter @Setter
    private Long id;

	@Getter @Setter
    private Funcionalidade funcionalidade;


    @Inject
    private FuncionalidadeService funcionalidadeService;


	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
        funcionalidade = new Funcionalidade();

	}


	public void carregar() {

		LOGGER.debug("Carregando Funcionalidade: {}", getId());
		funcionalidade = funcionalidadeService.findByIdFetchAll(getId());

	}

	@URLAction(mappingId = "alterarFuncionalidade", onPostback = false)
	public void configurarModoAlteracao() {
	   super.setModoAlteracao();
	   carregar();
	}

	@URLAction(mappingId = "incluirFuncionalidade", onPostback = false)
    public void configurarModoInclusao() {
        super.setModoInclusao();
    }


	@URLAction(mappingId = "visualizarFuncionalidade", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirFuncionalidade", onPostback = false)
	public void configurarModoExclusao() {
		super.setModoExclusao();
		carregar();

	}

	public void salvar() throws BusinessException {

		boolean inclusao = funcionalidade.getId() == null;

		LOGGER.debug("Persistindo Funcionalidade: {}", funcionalidade);
		
		funcionalidadeService.checkAndSave(funcionalidade);

		if (inclusao) {
			addInfoMessage(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			addInfoMessage(BeanMessageConstants.MSG_ALTERAR_SUCESSO);
		}
		
		setId(funcionalidade.getId());
		setModoVisualizacao();
	}
	
	public void excluir() {
		LOGGER.debug("Excluindo Funcionalidade: {}", id);
		funcionalidadeService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}
}
