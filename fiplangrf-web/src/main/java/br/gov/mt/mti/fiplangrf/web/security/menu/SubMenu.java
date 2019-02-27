package br.gov.mt.mti.fiplangrf.web.security.menu;

import javax.faces.component.FacesComponent;

import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;
import br.gov.mt.mti.fiplangrf.web.security.user.UserUtil;

@FacesComponent(SubMenu.COMPONENT_TYPE)
public class SubMenu extends org.primefaces.component.submenu.UISubmenu {

	public static final String COMPONENT_TYPE = "br.gov.mt.mti.fiplangrf.web.security.menu.SubMenu";

	private String permissao;

	public SubMenu() {
		super();
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		if (permissao == null || permissao.isEmpty()) {
			return;
		}

		UserDetail userDetail = UserUtil.getUserDetail();

		if (userDetail == null || !userDetail.getPermissaoItemMenu().containsKey(permissao)) {
			setRendered(false);
		} else {
			setRendered(true);
		}
		this.permissao = permissao;
	}
}