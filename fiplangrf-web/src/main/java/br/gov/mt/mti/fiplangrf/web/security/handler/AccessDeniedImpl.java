package br.gov.mt.mti.fiplangrf.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedImpl implements AccessDeniedHandler {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String msg = "<div class='alert alert-danger'><strong>Acesso negado a:</strong><p>" + request.getContextPath() + request.getServletPath() + "</p></div>";
		
		logger.info(accessDeniedException.getMessage());
		logger.info(request.getRequestURI());
		logger.info(msg);		
		
		String msgEncoded = Base64.encodeBase64URLSafeString(msg.getBytes());

		redirectStrategy.sendRedirect(request, response, "/acessoNegadoPopup?deniedMsg=" + msgEncoded);
		
	}

}