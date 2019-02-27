package br.gov.mt.mti.fiplangrf.util.mail;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2296856630454302195L;
	private String recipients;
	private String subject;
	private String text;
	private String content;
	
	public MailMessage() {
		super();
	}
}
