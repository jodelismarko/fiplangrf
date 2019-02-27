package br.gov.mt.mti.fiplangrf.web.security.authentication;

import lombok.Data;

@Data
public class FIPLANAuthenticationResponse implements Cloneable {

	private int codigoResponse;
	private String msg;

	@Override
	public FIPLANAuthenticationResponse clone() throws CloneNotSupportedException {
		FIPLANAuthenticationResponse o = new FIPLANAuthenticationResponse();
		o.setCodigoResponse(this.getCodigoResponse());
		o.setMsg(this.getMsg());
		return o;
	}

}
