package br.gov.mt.mti.fiplangrf.web.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;

import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;

public class MTIPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667702604761313616L;
	
	
	private static final String MSG_EXPIRACAO_SENHA_EXIBIDA="msgExpiracaoSenhaExibida";
	
	@Inject 
	UsuarioLogado usuarioLogado;
	
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    	if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
    		HttpSession session = ((HttpSession) event.getFacesContext().getExternalContext().getSession(false));
    		if (session!=null){    			
        		if (usuarioLogado!=null && session.getAttribute(MSG_EXPIRACAO_SENHA_EXIBIDA)==null){
        			//quando a diferença da validade de acesso e data atual for menor ou igual a 10 dias
        			if (DateUtils.addDays(new Date(), 10).getTime() > usuarioLogado.getDataValidade().getTime()){
        	              event.getFacesContext().addMessage(null, 
        	            		  new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção! O seu acesso ao SIND será até "+ new SimpleDateFormat("dd/MM/yyyy").format(usuarioLogado.getDataValidade()),null));
                          session.setAttribute(MSG_EXPIRACAO_SENHA_EXIBIDA, true);
        			}
      
        		}
    		}
        } 
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

}