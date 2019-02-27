package br.gov.mt.mti.fiplangrf.web.bean.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
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
import br.gov.mt.mti.fiplangrf.criteria.user.FuncionalidadeCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;
import lombok.Getter;
import lombok.Setter;

@Named("funcionalidadePesquisarBean")
@ViewScoped
@URLMappings(mappings={
	@URLMapping(id = "pesquisarFuncionalidade", pattern = "/funcionalidade/pesquisar", viewId = "/pages/user/funcionalidade/funcionalidadePesquisar.jsf"),
	@URLMapping(id = "pesquisarFuncionalidadePopup", pattern = "/funcionalidade/pesquisar/popup/#{paramBufferKey : funcionalidadePesquisarBean.paramBufferKey}/#{perfilId : funcionalidadePesquisarBean.perfilId}", viewId = "/pages/user/funcionalidade/funcionalidadePesquisarPopup.jsf")
})
public class FuncionalidadePesquisarBean extends AbstractPesquisaBean<FuncionalidadeCriteria> {

    private static final long serialVersionUID = -964348784388834747L;

	@Inject
    private Logger LOGGER;
	
	//Necessário apenas se utilizar um critério do tipo LUPA
	private Map<String, String> pageLupaMapping = new HashMap<String, String>();

	public static final String PESQUISAR_FUNCIONALIDADE_VIEW = "pretty:pesquisarFuncionalidade";

	@Getter @Setter
	private LazyObjectDataModel<Funcionalidade> resultadoPesquisa;
	
	@Getter @Setter
	private String paramBufferKey;
	
	@Getter @Setter
	private Long perfilId;
	
	private Set<Funcionalidade> funcionalidadesSelecionadas;

	@Inject
	private FuncionalidadeService funcionalidadeService;
	
	@Inject 
	private PerfilService perfilService;

	private Perfil perfil;
	
	@PostConstruct
	public void initialize() {
		limpar();
		this.pageLupaMapping.put("permissao.id", "/pages/user/permissao/lupaPermissao.xhtml");
	}

	public void pesquisar(ActionEvent event) {
		
		if(perfil != null && perfil.getFuncionalidades() != null) {
			getCriteria().setExceptFuncionalidadeId(perfil.getFuncionalidades());
		}
		
		LOGGER.debug("Pesquisando registros de Funcionalidade: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<Funcionalidade>(funcionalidadeService, getCriteria());

	}


	public void limpar() {

		setCriteria(new FuncionalidadeCriteria());
		resultadoPesquisa = null;

	}
	
	@URLAction(mappingId="pesquisarFuncionalidadePopup", onPostback=false)
	public void carregarPerfil() {
		if(getPerfilId() != null) {
			this.perfil = perfilService.loadById(getPerfilId());			
		}
	}

	public Set<Funcionalidade> getFuncionalidadesSelecionadas() {
		if(this.funcionalidadesSelecionadas == null) {
			setFuncionalidadesSelecionadas(new HashSet<Funcionalidade>());
		}
		return funcionalidadesSelecionadas;
	}

	public void setFuncionalidadesSelecionadas(Set<Funcionalidade> funcionalidadesSelecionadas) {
		this.funcionalidadesSelecionadas = funcionalidadesSelecionadas;
	}
	
	@SuppressWarnings("unchecked")
	public void onRowSelectCheckBox(SelectEvent event) {
		Funcionalidade funcionalidade = (Funcionalidade) event.getObject();
		getFuncionalidadesSelecionadas().add(funcionalidade);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Funcionalidade> funcionalidades = (Set<Funcionalidade>)getSessionBuffer().get(getParamBufferKey()); 
			funcionalidades.add(funcionalidade);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onRowUnselectCheckBox(UnselectEvent event) {
		Funcionalidade funcionalidade = (Funcionalidade) event.getObject();
		getFuncionalidadesSelecionadas().remove(funcionalidade);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Funcionalidade> funcionalidades = (Set<Funcionalidade>)getSessionBuffer().get(getParamBufferKey());
			funcionalidades.remove(funcionalidade);
		}
	}

	@Override
	public Map<String, String> getPageMappingLupa() {
		return this.pageLupaMapping;
	}
}

