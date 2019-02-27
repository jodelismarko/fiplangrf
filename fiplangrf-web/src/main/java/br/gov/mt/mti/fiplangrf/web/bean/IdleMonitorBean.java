package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
public class IdleMonitorBean implements Serializable {
	
	public static final String MESSAGE_MONITOR = "messageMonitor";
    /**
	 * 
	 */
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static final long serialVersionUID = 1610661144086364381L;

	public void onIdle() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		logger.info(request.getRequestURI());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,  "Sem atividade.", "...");		
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
 
    public void onActive() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	logger.info(request.getRequestURI());
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Bem vindo", "Bem, esta foi uma longa pausa para o café!");
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
}
