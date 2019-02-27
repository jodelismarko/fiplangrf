package br.gov.mt.mti.fiplangrf.web.bean.user.Perfil;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.user.PerfilCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;
import lombok.Getter;
import lombok.Setter;

@Named("perfilPesquisarBean")
@ViewScoped
@URLMappings(
	mappings= {
			@URLMapping(id = "pesquisarPerfil", pattern = "/perfil/pesquisar", viewId = "/pages/user/perfil/perfilPesquisar.jsf"),
			@URLMapping(id = "pesquisarPerfilPopup", pattern = "/perfil/pesquisar/popup/#{paramBufferKey : perfilPesquisarBean.paramBufferKey}/#{usuarioId : perfilPesquisarBean.usuarioId}", viewId = "/pages/user/perfil/perfilPesquisarPopup.jsf")	
})
public class PerfilPesquisarBean extends AbstractPesquisaBean<PerfilCriteria> {

    private static final long serialVersionUID = 280988742687402291L;

	@Inject
    private Logger LOGGER;

	public static final String PESQUISAR_PERFIL_VIEW = "pretty:pesquisarPerfil";
	
	public static final String PERMISSAO_PESQUISAR_PERFIL = "pesquisar.perfil";

	@Getter @Setter
	private LazyObjectDataModel<Perfil> resultadoPesquisa;
	
	@Getter @Setter
	private String paramBufferKey;	
	
	@Getter @Setter
	private Perfil perfilSelecionado;
	
	private Set<Perfil> perfisSelecionados;
	
	@Inject
	private PerfilService perfilService;
	
	@Inject
	private UsuarioService usuarioService;
	
	private Usuario usuario;
	
	@Getter @Setter
	private Long usuarioId;

	@PostConstruct
	public void initialize() {
		limpar();
	}

	public void pesquisar(ActionEvent event) {
		setAction(PERMISSAO_PESQUISAR_PERFIL);

		LOGGER.debug("Pesquisando registros de Perfil: {}", getCriteria());
		
		if(usuario != null && usuario.getPerfis() != null) {
			getCriteria().setExceptPerfilId(usuario.getPerfis());
		}
		
		resultadoPesquisa = new LazyObjectDataModel<Perfil>(perfilService, getCriteria());

	}

	public void limpar() {

		setCriteria(new PerfilCriteria());
		resultadoPesquisa = null;

	}
	
	@URLAction(mappingId="pesquisarPerfilPopup", onPostback=false)
	public void carregarUsuario() {
		if(getUsuarioId() != null) {
			this.usuario = usuarioService.loadById(getUsuarioId());
		}
	}

	public void onRowSelectRadio(SelectEvent event) {
		setPerfilSelecionado((Perfil) event.getObject());
		if(getParamBufferKey() != null && !getParamBufferKey().isEmpty()) {
			getSessionBuffer().put(getParamBufferKey(), getPerfilSelecionado());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onRowSelectCheckBox(SelectEvent event) {
		Perfil perfil = (Perfil) event.getObject();
		getPerfisSelecionados().add(perfil);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Perfil> perfis = (Set<Perfil>)getSessionBuffer().get(getParamBufferKey()); 
			perfis.add(perfil);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onRowUnselectCheckBox(UnselectEvent event) {
		Perfil perfil = (Perfil) event.getObject();
		getPerfisSelecionados().remove(perfil);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Perfil> perfis = (Set<Perfil>)getSessionBuffer().get(getParamBufferKey());
			perfis.remove(perfil);
		}
	}

	@PreDestroy
	public void destroy() {
	}
	
	/*
	 * Getters and Setters
	 */
	public Set<Perfil> getPerfisSelecionados() {
		if(this.perfisSelecionados == null) {
			setPerfisSelecionados(new HashSet<Perfil>());
		}
		return perfisSelecionados;
	}

	public void setPerfisSelecionados(Set<Perfil> perfisSelecionados) {
		this.perfisSelecionados = perfisSelecionados;
	}

}
