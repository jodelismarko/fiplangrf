package br.gov.mt.mti.fiplangrf.web.security.authentication;

import org.springframework.security.authentication.BadCredentialsException;

import br.gov.mt.mti.fiplangrf.web.bean.ApplicationInfoBean;

public class FIPLANBadCredentialsException extends BadCredentialsException {

	private static final long serialVersionUID = 7905551877393108196L;

	public FIPLANBadCredentialsException(String msg) {
		super(msg);
		FIPLANAuthenticationResponse resp = new FIPLANAuthenticationResponse();
		resp.setMsg(msg);
		resp.setCodigoResponse(2);
		ApplicationInfoBean.setAuthentication(resp);
	}

	public FIPLANBadCredentialsException(String msg, FIPLANAuthenticationResponse resp) {
		super(msg);
		ApplicationInfoBean.setAuthentication(resp);
	}
	}
