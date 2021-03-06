package br.gov.mt.mti.fiplangrf.web.security.menu;

import javax.faces.component.FacesComponent;

import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;

@FacesComponent(Button.COMPONENT_TYPE)
public class Button extends org.primefaces.component.button.Button {
	
	public static final String COMPONENT_TYPE = "br.gov.mt.mti.fiplangrf.web.security.menu.Button";
	
	private String permissao;

	public Button() {
		super();
	}
	
	@Override
	public String getOnclick() {
		return super.getOnclick();
	}
	
	public String getPermissao() {
		return permissao;
	}
	
	public void setPermissao(String permissao) {
		
		UserDetail user = null;
		
		setOnclick("remotePerm([{name:'itemMenuAcessado',value:'" + permissao + "'}]);" + ((super.getOnclick() != null) ? super.getOnclick() : ""));
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof br.gov.mt.mti.fiplangrf.web.security.user.UserDetail) {
				user = (br.gov.mt.mti.fiplangrf.web.security.user.UserDetail)principal;
			} else {
				setRendered(true);
				return;
			}
		}
		
		if(user == null || !user.getPermissaoItemMenu().containsKey(permissao.toLowerCase())) {
			setRendered(false);
		}
		this.permissao = permissao;
	}
}
