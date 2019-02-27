package br.gov.mt.mti.fiplangrf.web.bean.user.Perfil;

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
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Named("perfilVincularBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "vincularPerfilFuncionalidade", pattern = "/perfil/vincular/#{ id : perfilVincularBean.id}", viewId = "/pages/user/perfil/perfilVincularFuncionalidade.jsf")
		})
public class PerfilVincularBean extends AbstractManterBean {

    private static final long serialVersionUID = -184723293394267565L;

    @Inject
    private Logger LOGGER;

	public static final String VINCULAR_PERFIL_VIEW = "pretty:vincularPerfilFuncionalidade";
	
	public static final String PERMISSAO_ACTION = "perfil.vincular.funcionalidade";	

	@Getter @Setter
    private Long id;

	@Getter @Setter
    private Perfil perfil;

    @Inject
    private PerfilService perfilService;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		if(getSessionBuffer().get(getBufferKey()) == null) {
			getSessionBuffer().put(getBufferKey(), new HashSet<Funcionalidade>());
		}
		limpar();
	}

	public void limpar() {
        perfil = new Perfil();
	}

	public void carregar() {
		LOGGER.debug("Carregando Perfil: {}", getId());
		perfil = perfilService.loadById(getId());
	}

	@URLAction(mappingId = "vincularPerfilFuncionalidade", onPostback = false)
	public void configurarModoAlteracao() {
	   super.setModoAlteracao();	   
	   setAction(PERMISSAO_ACTION);
	   carregar();
	}
	
	public void salvar() throws BusinessException {

		boolean inclusao = perfil.getId() == null;

		LOGGER.debug("Persistindo Perfil: {}", perfil);
		
		perfilService.saveOrUpdate(perfil);

		if (inclusao) {
			showMainMsgDialog(BeanMessageConstants.MSG_INCLUIR_SUCESSO);
		} else {
			showMainMsgDialog(BeanMessageConstants.MSG_ALTERAR_SUCESSO, ButtonScript.RETURN);
		}
	}
	
	public void excluir(Funcionalidade funcionalidade) {
		getPerfil().getFuncionalidades().remove(funcionalidade);
	}
	
	public void updateFuncionalidades(){		
		@SuppressWarnings("unchecked")
		Set<Funcionalidade> funcionalidadesSelecionadas = (Set<Funcionalidade>)getSessionBuffer().remove(getBufferKey());
		if(funcionalidadesSelecionadas != null) {
			if(getPerfil() == null){
				setPerfil(new Perfil());
			}
			if(getPerfil().getFuncionalidades() == null){
				getPerfil().setFuncionalidades(new HashSet<Funcionalidade>());
			}
			
			getPerfil().getFuncionalidades().addAll(funcionalidadesSelecionadas);
		}
		
	}
}
