package br.gov.mt.mti.fiplangrf.common.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;

@SuppressWarnings("all")
public class Env {
	
	final static private String PROPRIEDADE_URL_SEFAZ = "wsURLSefaz";
	final static private String PROPRIEDADE_USUARIO_SEFAZ = "wsUsuarioSefaz";
	final static private String PROPRIEDADE_SENHA_SEFAZ   = "wsSenhaSefaz"; 
	final static private String PROPRIEDADE_CODIGO_ORGAO_SEFAZ   = "wsCodigoOrgao";
	
	public static UsuarioLogado getUsuarioLogado(){
		
		if (FacesContext.getCurrentInstance() == null) {
			return null;
		}
		
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		if (httpSession != null) {
			UsuarioLogado usuarioLogado = (UsuarioLogado) httpSession.getAttribute(UsuarioLogado.USUARIO_LOGADO_KEY);

			return usuarioLogado;
		}

		return null;
	}
	

	public static String getWSUsuarioSefaz() {
		
		return EnvUtil.getProperties(PROPRIEDADE_USUARIO_SEFAZ);
	}
	
	public static String getWSSenhaSefaz() {
			
		return EnvUtil.getProperties(PROPRIEDADE_SENHA_SEFAZ);
	}
	
	public static String getWSURLSefaz() {
		
		return EnvUtil.getProperties(PROPRIEDADE_URL_SEFAZ);
	}
	
public static String getWSCodigoOrgaoSefaz() {
		
		return EnvUtil.getProperties(PROPRIEDADE_CODIGO_ORGAO_SEFAZ);
	}
	
}
