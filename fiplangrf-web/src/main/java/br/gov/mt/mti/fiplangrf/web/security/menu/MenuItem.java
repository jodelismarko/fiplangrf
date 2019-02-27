package br.gov.mt.mti.fiplangrf.web.security.menu;

import javax.faces.application.ResourceDependencies;
import javax.faces.component.FacesComponent;

import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;

@ResourceDependencies({

})

@FacesComponent(MenuItem.COMPONENT_TYPE)
public class MenuItem extends org.primefaces.component.menuitem.UIMenuItem {	
	
	public static final String COMPONENT_TYPE =   "br.gov.mt.mti.fiplangrf.web.security.menu.MenuItem";
	public static final String COMPONENT_FAMILY = "br.gov.mt.mti.fiplangrf.web.security";
	
//	public static final String COMPONENT_TYPE = "org.primefaces.component.UIMenuItem";
//	public static final String COMPONENT_FAMILY = "org.primefaces.component";
	
	private String permissao;
	
	public MenuItem() {
		super();
	}

	public String getPermissao() {
		return permissao;
	}
	
	@Override
	public String getOnclick() {
		return super.getOnclick();
	}
	
	public void setPermissao(String permissao) {
		
		UserDetail user = null;
		
		super.setOnclick(Constantes.RC_PERMISSAO + "([{name:'itemMenuAcessado',value:'" + permissao + "'}, {name:'urlSys',value:this.href}]);" + ((super.getOnclick() != null) ? super.getOnclick() : " return false;"));
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof br.gov.mt.mti.fiplangrf.web.security.user.UserDetail) {
				user = (br.gov.mt.mti.fiplangrf.web.security.user.UserDetail)principal;
			} else {
				setRendered(true);
				return;
			}
		}
		
		if(user == null || !user.getPermissaoItemMenu().containsKey(permissao.toLowerCase())){
			setRendered(false);
		}
		this.permissao = permissao;
	}
}