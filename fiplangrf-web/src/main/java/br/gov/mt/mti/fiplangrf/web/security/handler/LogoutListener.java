package br.gov.mt.mti.fiplangrf.web.security.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcLocal;
import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	@Autowired UsuarioLoginSvcLocal usuarioSvcLocal;
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetail userDetail;
        for (SecurityContext securityContext : lstSecurityContext)
        {
        	Object principal = securityContext.getAuthentication().getPrincipal();
        	
        	if(principal instanceof br.gov.mt.mti.fiplangrf.web.security.user.UserDetail) {
	            userDetail = (UserDetail) securityContext.getAuthentication().getPrincipal();           
	            Usuario update = new Usuario();
	            update.setCodigo(userDetail.getCodigo());
	            usuarioSvcLocal.updateDataLoginNULL(update);
        	} else {
        		Usuario update = new Usuario();
        		update.setCodigo(1L); //Id do master
        		usuarioSvcLocal.updateDataLoginNULL(update);
        	}         	
        }
    }

}