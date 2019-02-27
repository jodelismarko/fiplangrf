package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.service.security.GenericService;
import br.gov.mt.mti.fiplangrf.web.bean.util.ImageUploadBean;

@Named("menuBean")
@SessionScoped
@URLMappings(mappings= {
		@URLMapping(id = "home", pattern = "/home", viewId = "/home.jsf"),
		@URLMapping(id = "homePopup", pattern = "/homePopup", viewId = "/homePopup.jsf")
})
@SuppressWarnings("all")
public class MenuBean implements Serializable {
	
	public static final String HOME_VIEW = "pretty:home";
	
	@Inject
	private UsuarioLogado usuarioLogado;
	@Inject
	private GenericService genericService;
	
	private String textoBusca;
	
	private static final long serialVersionUID = 3514493845071277719L;
	
	private static final String MENU_ACESSADO = "itemMenuAcessado";
	
	@PostConstruct
	public void init() {
	}
	
	public void handleExpiredSession() {
    	if(!usuarioLogado.isExpired() && !genericService.isLoggedIn(usuarioLogado)) {
    		usuarioLogado.setExpired(true);    		
    	}
    	
    	RequestContext reqCtx = RequestContext.getCurrentInstance();        
    	
    	if(usuarioLogado.isExpired()) {
    		reqCtx.addCallbackParam(Constantes.RETURNED_VALUE, Constantes.LOGOUT);
    	} else {
    		reqCtx.addCallbackParam(Constantes.RETURNED_VALUE, Constantes.STAY);
    	}
	}
	
	public void setPermissao(ActionEvent event){
		
		String itemMenuAcessado = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(MENU_ACESSADO);
		String urlSys = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("urlSys");
		urlSys = urlSys.replaceFirst(".*fiplangrf", "");
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = request.getRequestURL().toString();
		
		if( ! url.contains("manterUsuario") ) {
			ImageUploadBean.selfDestruction();
		}
		
		usuarioLogado.setAction(itemMenuAcessado);
		RequestContext.getCurrentInstance().addCallbackParam("urlSys", urlSys);
	}
	
	public String getTextoBusca() {
		return textoBusca;
	}

	public void setTextoBusca(String textoBusca) {
		this.textoBusca = textoBusca;
	}

	@URLAction(mappingId = "home", onPostback = false)
	public void inicializarHome() {
		this.textoBusca = "";
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public StreamedContent getImagemPerfil() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/default-user.jpg");
		StreamedContent imgDefault = new DefaultStreamedContent(stream, "image/jpeg");
		return (usuarioLogado.getImagemPerfil() != null ?
				new DefaultStreamedContent(new ByteArrayInputStream(usuarioLogado.getImagemPerfil())) : imgDefault);
	}

}