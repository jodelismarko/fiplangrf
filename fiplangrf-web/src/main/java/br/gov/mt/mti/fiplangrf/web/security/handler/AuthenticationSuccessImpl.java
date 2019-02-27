package br.gov.mt.mti.fiplangrf.web.security.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcLocal;
import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;

@Component
public class AuthenticationSuccessImpl implements AuthenticationSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();    
    
    @Autowired UsuarioLoginSvcLocal usuarioSvcLocal;
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication auth) throws IOException, ServletException {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		HttpSession session = request.getSession(false);
		
		if (principal instanceof br.gov.mt.mti.fiplangrf.web.security.user.UserDetail) {
			UserDetail userDetail = (UserDetail) principal;	
			userDetail.setSessionId(session.getId());
			setUsuarioLogado(request, userDetail.getUsername());
		} else {			
			if("999.999.999-99".equals(((User)principal).getUsername())) {
				setUsuarioLogado(request, "36329059918");
			}
		}
		
		redirectStrategy.sendRedirect(request, response, "/home");
	}

	private void setUsuarioLogado(HttpServletRequest request, String userName) {
		HttpSession session = request.getSession(false);
		Usuario usuario = (Usuario) usuarioSvcLocal.findByLogin(userName);
		usuario.setDataUltimoAcesso(new Date());
		usuarioSvcLocal.updateDataLogin(usuario, request.getLocalAddr());
		if (session != null) {
			UsuarioLogado envers = new UsuarioLogado();
			envers.setCodigo(usuario.getCodigo());
			envers.setCpf(usuario.getLogin());
			envers.setNome(usuario.getFiplanUsuario().getNome());
			envers.setImagemPerfil(usuario.getFoto());
			String usuarioNome = usuario.getFiplanUsuario().getNome();
			String[] listaNomes = usuarioNome.split(" ");
			String nomeSobrenome = listaNomes.length > 1 ? listaNomes[0] + ' ' + listaNomes[listaNomes.length - 1]
					: listaNomes[0];
			envers.setNomeSobrenome(nomeSobrenome);
			envers.setSessionCreatedTime(new Date(session.getCreationTime()));
			envers.setIp(request.getRemoteAddr());
			session.setAttribute(UsuarioLogado.USUARIO_LOGADO_KEY, envers);
		}
	}
}
