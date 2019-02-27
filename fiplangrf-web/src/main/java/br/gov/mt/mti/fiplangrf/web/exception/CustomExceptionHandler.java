package br.gov.mt.mti.fiplangrf.web.exception;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.ocpsoft.pretty.PrettyContext;

import br.gov.mt.cepromat.ceprofw.common.util.ExceptionUtil;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.service.security.exception.LogoutException;
import br.gov.mt.mti.fiplangrf.service.security.exception.PermissaoNegadaException;

@SuppressWarnings("all")
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    //Obtém uma instância do FacesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    //Obtém um mapa do FacesContext    
	final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    //Obtém o estado atual da navegação entre páginas do JSF
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    //Declara o construtor que recebe uma exceptio do tipo ExceptionHandler como parâmetro
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    //Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    //Sobrescreve o método handle que é responsável por manipular as exceções do JSF    
	@Override
    public void handle() throws FacesException {
 
        final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
        
        FacesContext faces = FacesContext.getCurrentInstance();
        
        String message = null;
        
        HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            
 
            Throwable exception = ExceptionUtils.getRootCause(context.getException());
            if(exception == null) {
            	exception = context.getException();
            }
            
            exception.printStackTrace();
            
            String phaseId = PrettyContext.getCurrentInstance().getCurrentViewId();
            
            String keyException = PermissaoNegadaException.class.getName();
            Map<String, Object> requestMap = faces.getExternalContext().getRequestMap();
            
            if(requestMap.containsKey(keyException)){
            	
            	message = (String)requestMap.get(keyException);
            	
//            	requestMap.put("javax.servlet.error.exception_type", keyException);
//            	requestMap.put("javax.servlet.error.message", message);
//            	requestMap.put("javax.servlet.error.status_code", "403");
//            	<li class="list-group-item list-group-item-danger">User agent: #{header['user-agent']}</li>
//			    <li class="list-group-item list-group-item-danger">User IP: #{request.remoteAddr}</li>
//			    <!--<li class="list-group-item list-group-item-danger">Request URI: #{requestScope['javax.servlet.error.request_uri']}</li>-->
//			    <!--<li class="list-group-item list-group-item-danger">Ajax request: #{facesContext.partialViewContext.ajaxRequest ? 'Yes' : 'No'}</li> -->
//			    <!-- <li class="list-group-item list-group-item-danger">Status code: #{requestScope['javax.servlet.error.status_code']}</li> -->
//			    <li class="list-group-item list-group-item-danger">Exception type: #{requestScope['javax.servlet.error.exception_type']}</li>
//			    <li class="list-group-item list-group-item-danger">Exception message: #{requestScope['javax.servlet.error.message']}</li>
//            	
            }
            
            try { 
            	
            	String messageDetail = null;
            	if(message == null) {
            		messageDetail = exception.getMessage();
            	} else {
            		messageDetail = message;
            	}
 
                if(exception instanceof PermissaoNegadaException) {
	                String msgEncoded = Base64.encodeBase64URLSafeString(messageDetail.getBytes());
	                
	                boolean ajaxRedirect = request.getHeader("faces-request") != null
	        	            && request.getHeader("faces-request").toLowerCase().indexOf("ajax") > -1;
	        	    
	        	    String popupParameter = (String) ObjectUtils.firstNonNull(request.getParameter("popup"), request.getAttribute("popupRelatorio"));
	        	    boolean isPopup = (request.getHeader("popup") != null && "true".equalsIgnoreCase(request.getHeader("popup")));
	        	    
	        	    if(ajaxRedirect) {       
	        	    	String url = "acessoNegado?deniedMsg=" + msgEncoded;
	        	    	if(isPopup) {
	        	    		url = "acessoNegadoPopup";
	        	    		response.setHeader("sindMsgError", msgEncoded);
	        	    	}
	        	    	response.sendError(411, url);
	        	    } else {
	        	    	String url = (popupParameter != null && "true".equalsIgnoreCase(popupParameter)) ? 
	        	    			("/access-denied-popup.jsf?faces-redirect=true&deniedMsg=" + msgEncoded) : ("/access-denied.jsf?faces-redirect=true&deniedMsg=" + msgEncoded);
	        	    	if(request.getAttribute("popupRelatorio") != null && request.getAttribute("popupRelatorio").equals("true")) {
	        	    		url += "&popupRelatorio=true";
	        	    	}
	        	    	navigationHandler.handleNavigation(facesContext, null, url);
	        	    }
                } else if(exception instanceof LogoutException) {
                	
                	HttpSession session = (HttpSession) request.getSession(false);
                	
                	if(session != null) {
                		session.invalidate();
                	}
                	
                } else if(exception instanceof NullPointerException){
                	
                	//exception.printStackTrace();
                	
                } else {
                	Throwable exceptionCause = ExceptionUtil.findException(exception, org.hibernate.exception.ConstraintViolationException.class);
                	
                	if(exceptionCause instanceof SQLIntegrityConstraintViolationException) {
            			SQLIntegrityConstraintViolationException sqlCons = (SQLIntegrityConstraintViolationException)exceptionCause;
            			switch(sqlCons.getErrorCode()) {
            				case 2292:
            					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível efetuar a exclusão do registro, pois existem relacionamentos que impedem a exclusão.", ""));	                	
        	                	facesContext.renderResponse();
            					break;
            				case 1:
            					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível efetuar a inclusão/alteração do registro, restrição exclusiva.", ""));	                	
        	                	facesContext.renderResponse();
            					break;            				
            			} 
            		} else if(exception instanceof BusinessException) {
	                	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messageDetail, ""));	                	
	                	facesContext.renderResponse();
                	}
                }
            } catch (IOException e) {
				e.printStackTrace();
			} finally {
                // Remove a exeção da fila
                iterator.remove();
            }
        }
        // Manipula o erro
        getWrapped().handle();
    }
	
}