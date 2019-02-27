package br.gov.mt.mti.fiplangrf.web.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("acessoNegadoBean")
@SuppressWarnings("rawtypes")
@RequestScoped
@URLMappings(
			mappings = { 
					@URLMapping(id = "acessoNegado", pattern = "/acessoNegado", viewId = "/access-denied.jsf"), 
					@URLMapping(id = "acessoNegadoPopup", pattern = "/acessoNegadoPopup", viewId = "/access-denied-popup.jsf")
					}			
			)
public class AcessoNegadoBean extends AbstractPesquisaBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(AcessoNegadoBean.class);

	private static final long serialVersionUID = 1493194205883289953L;
	private String message;
	private FacesContext facesContext = getFacesContext();
	
	@PostConstruct
	public void inicializar() {
		LOGGER.debug("Carregando Acesso Negado: {}", getMessage());
	}
	
	public String decodeMessage(){
		if(facesContext.getExternalContext().getRequestParameterValuesMap().containsKey("deniedMsg")){
			String[] message = ((String[])facesContext.getExternalContext().getRequestParameterValuesMap().get("deniedMsg"));
			String encodedMessage = (message != null && message.length > 0) ? message[0] : "";
			if(encodedMessage.isEmpty()) 
				return "";
			
			byte[] decoded = Base64.decodeBase64(encodedMessage);
			
			return new String(decoded);
			
		}
		return null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
