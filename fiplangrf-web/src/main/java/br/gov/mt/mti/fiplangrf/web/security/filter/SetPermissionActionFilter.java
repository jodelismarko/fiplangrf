package br.gov.mt.mti.fiplangrf.web.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;

@WebFilter(filterName="SetPermissionActionFilter", urlPatterns="/*")
public class SetPermissionActionFilter implements Filter, Constantes {

	public static final String HEADER_NAME = MTIFMW_ACTION;	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		
//		if(isAJAXRequest(req)){
//	        chain.doFilter(request, response);
//	        return;
//	    }
		
		if(!req.getRequestURL().toString().contains("javax.faces.resource") &&
		   !req.getRequestURL().toString().endsWith(".js")) {
		
			UsuarioLogado usuarioLogado = null;
			
			String ajxIdleMonitor = ObjectUtils.firstNonNull(req.getParameter("ajxIdleMonitor"), "false");
			if(ajxIdleMonitor == null || ajxIdleMonitor.equals("false")) {
				if(session != null) {
					usuarioLogado = (UsuarioLogado) session.getAttribute(UsuarioLogado.USUARIO_LOGADO_KEY);
				}
				
				String action = req.getHeader(HEADER_NAME);			
				
				if(StringUtils.isEmpty(action)) {
					
					action =  request.getParameter(HEADER_NAME);
					
				}
				
				if(StringUtils.isNotEmpty(action) && usuarioLogado != null) {
					usuarioLogado.setAction(action);				
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	public boolean isAJAXRequest(HttpServletRequest request) {
	    boolean check = false;
	    String facesRequest = request.getHeader("Faces-Request");
	    if (facesRequest != null && facesRequest.equals("partial/ajax")) {
	        check = true;
	    }
	    return check; 
	}
	
	@Override
	public void destroy() {
	}
}
