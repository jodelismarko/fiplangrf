package br.gov.mt.mti.fiplangrf.web.bean.user;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.web.bean.BasePesquisaBean;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.service.security.user.PermissaoService;

@ViewScoped
@Named("lupaPermissaoBean")
@URLMappings(mappings = {
		@URLMapping(id = "lupaPermissao", pattern = "/pages/user/lupa/permissao", viewId = "/pages/user/permissao/lupaPermissao.jsf") })
public class LupaPermissaoBean implements Serializable {

	private static final long serialVersionUID = -734362468283761692L;

	private Permissao permissaoSelecionada;
	
	@Inject
	private PermissaoService permissaoService;

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	public void selectPermissaoFromDialog(BasePesquisaBean<?> basePesquisaBean) {
		if (getPermissaoSelecionada() == null) {
			FacesMessage message = new FacesMessage("Informe a permissão.", "Informe o permissão.");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			return;
		}
		basePesquisaBean.closeDialogLupa(getPermissaoSelecionada());
	}
	
	public List<Permissao> getListaPermissao(){
		return permissaoService.findAll();
	}
}

