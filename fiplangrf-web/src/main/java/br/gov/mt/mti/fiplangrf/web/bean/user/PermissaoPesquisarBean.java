package br.gov.mt.mti.fiplangrf.web.bean.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import br.gov.mt.mti.fiplangrf.criteria.user.PermissaoCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService;
import br.gov.mt.mti.fiplangrf.service.security.user.PermissaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("permissaoPesquisarBean")
@ViewScoped
@URLMappings(mappings= {
		@URLMapping(id = "pesquisarPermissao", pattern = "/dev/permissao/pesquisar", viewId = "/pages/user/permissao/permissaoPesquisar.jsf"),
		@URLMapping(id = "pesquisarPermissaoPopup", pattern = "/dev/permissao/pesquisar/popup/#{paramBufferKey : permissaoPesquisarBean.paramBufferKey}/#{funcionalidadeId : permissaoPesquisarBean.funcionalidadeId}", viewId = "/pages/user/permissao/permissaoPesquisarPopup.jsf")
})
public class PermissaoPesquisarBean extends AbstractPesquisaBean<PermissaoCriteria> {

    private static final long serialVersionUID = 763841423797914100L;

    @Inject
    private Logger LOGGER;

	public static final String PESQUISAR_PERMISSAO_VIEW = "pretty:pesquisarPermissao";

	private LazyObjectDataModel<Permissao> resultadoPesquisa;
	
	private Long funcionalidadeId;
	
	private Funcionalidade funcionalidade;
	
	private String paramBufferKey;
	private Set<Permissao> permissoesSelecionadas;
	
	private List<Permissao> selecionados = new ArrayList<Permissao>();

	@Inject
	private FuncionalidadeService funcionalidadeService;
	
	@Inject
	private PermissaoService permissaoService;

	@PostConstruct
	public void initialize() {
		limpar();
	}

	public void pesquisar(ActionEvent event) {

		LOGGER.debug("Pesquisando registros de Permissao: {}", getCriteria());
		
		if(funcionalidade != null && funcionalidade.getPermissoes() != null) {
			getCriteria().setExceptPermissaoId(funcionalidade.getPermissoes());
		}
		
		resultadoPesquisa = new LazyObjectDataModel<Permissao>(permissaoService, getCriteria());

	}

	public void limpar() {

		setCriteria(new PermissaoCriteria());
		resultadoPesquisa = null;

	}

	@URLAction(mappingId="pesquisarPermissaoPopup", onPostback=false)
	public void carregarFuncionalidade() {
		if(getFuncionalidadeId() != null) {
			setFuncionalidade(funcionalidadeService.loadById(getFuncionalidadeId()));
		}
	}
	
	public LazyObjectDataModel<Permissao> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Permissao> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	public String getParamBufferKey() {
		return paramBufferKey;
	}

	public void setParamBufferKey(String bufferKey) {
		this.paramBufferKey = bufferKey;
	}

	public Set<Permissao> getPermissoesSelecionadas() {
		if(this.permissoesSelecionadas == null) {
			setPermissoesSelecionadas(new HashSet<Permissao>());
		}
		return permissoesSelecionadas;
	}

	public void setPermissoesSelecionadas(Set<Permissao> permissoesSelecionadas) {
		this.permissoesSelecionadas = permissoesSelecionadas;
	}
	
	@SuppressWarnings("unchecked")
	public void onRowSelectCheckBox(SelectEvent event) {
		Permissao permissao = (Permissao) event.getObject();
		getPermissoesSelecionadas().add(permissao);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Permissao> permissoes = (Set<Permissao>)getSessionBuffer().get(getParamBufferKey()); 
			permissoes.add(permissao);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onRowUnselectCheckBox(UnselectEvent event) {
		Permissao permissao = (Permissao) event.getObject();
		getPermissoesSelecionadas().remove(permissao);
		if(getSessionBuffer().get(getParamBufferKey()) != null) {
			Set<Permissao> permissoes = (Set<Permissao>)getSessionBuffer().get(getParamBufferKey());
			permissoes.remove(permissao);
		}
	}

	public List<Permissao> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Permissao> selecionados) {
		this.selecionados = selecionados;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Long getFuncionalidadeId() {
		return funcionalidadeId;
	}

	public void setFuncionalidadeId(Long funcionalidadeId) {
		this.funcionalidadeId = funcionalidadeId;
	}
}
