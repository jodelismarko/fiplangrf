package br.gov.mt.mti.fiplangrf.service.security.exception;

import javax.ejb.ApplicationException;

@ApplicationException(inherited=true, rollback=true)
public class PermissaoNegadaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6878733677688124147L;

	public PermissaoNegadaException() {
		super();
	}

	public PermissaoNegadaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PermissaoNegadaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PermissaoNegadaException(String arg0) {
		super(arg0);
	}

	public PermissaoNegadaException(Throwable arg0) {
		super(arg0);
	}

}
