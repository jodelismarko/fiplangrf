package br.gov.mt.mti.fiplangrf.web.bean.user;

import java.util.HashSet;
import java.util.Set;

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
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;

@Named("funcionalidadeVincularBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "vincularFuncionalidadePermissao", pattern = "/dev/funcionalidade/vincular/#{ id : funcionalidadeVincularBean.id}", viewId = "/pages/user/funcionalidade/funcionalidadeVincularPermissao.jsf")
		})
public class FuncionalidadeVincularBean extends AbstractManterBean {

    private static final long serialVersionUID = -184723293394267565L;

	@Inject
    private Logger LOGGER;

	public static final String VINCULAR_PERFIL_VIEW = "pretty:vincularFuncionalidadePermissao";
	
	public static final String PERMISSAO_ACTION = "funcionalidade.vincular.permissao";

    private Long id;

    private Funcionalidade funcionalidade;
    
    @Inject
    private FuncionalidadeService funcionalidadeService;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		if(getSessionBuffer().get(getBufferKey()) == null) {
			getSessionBuffer().put(getBufferKey(), new HashSet<Permissao>());
		}
		limpar();
	}

	public void limpar() {
        funcionalidade = new Funcionalidade();
	}

	public void carregar() {
		LOGGER.debug("Carregando Funcionalidade: {}", getId());
		funcionalidade = funcionalidadeService.loadById(getId());
	}

	@URLAction(mappingId = "vincularFuncionalidadePermissao", onPostback = false)
	public void configurarModoAlteracao() {
	   super.setModoAlteracao();	   
	   setAction(PERMISSAO_ACTION);
	   carregar();
	}
	
	public void salvar() throws BusinessException {

		boolean inclusao = funcionalidade.getId() == null;

		LOGGER.debug("Persistindo Funcionalidade: {}", funcionalidade);
		funcionalidadeService.saveOrUpdate(funcionalidade);

		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.RETURN);
		}
	}
	
	public void excluir(Permissao permissao) {
		getFuncionalidade().getPermissoes().remove(permissao);
	}
	
	public void updatePermissoes(){
		@SuppressWarnings("unchecked")
		Set<Permissao> permissoesSelecionadas = (Set<Permissao>)getSessionBuffer().remove(getBufferKey());
		if(permissoesSelecionadas != null) {
			if(getFuncionalidade() == null){
				setFuncionalidade(new Funcionalidade());
			}
			if(getFuncionalidade().getPermissoes() == null){
				getFuncionalidade().setPermissoes(new HashSet<Permissao>());
			}
			
			funcionalidade.getPermissoes().addAll(permissoesSelecionadas);
		}
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionalidade getFuncionalidade() {
        return this.funcionalidade;
    }

    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }
}
