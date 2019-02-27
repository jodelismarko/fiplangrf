package br.gov.mt.mti.fiplangrf.util.mail;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.picketbox.util.StringUtil;

public class JavaMailService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174373955544396892L;
	
	private Properties config;
	
	public static JavaMailService getInstance() {
		return new JavaMailService();
	}
	
	private JavaMailService() {
		config = new Properties();		
		config.put("mail.smtp.host", "192.168.0.74");
		config.put("mail.protocol.ssl.trust", "192.168.0.74");
		config.put("mail.smtp.socketFactory.port", "465");
		config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		config.put("mail.smtp.auth", "true");
		config.put("mail.smtp.ssl.enable", "true");
		config.put("mail.smtp.port", "465");
	}
	
	public void send(MailMessage mailMessage) {
		if(mailMessage == null) {
			throw new IllegalArgumentException("O Object mailMessage não pode ser igual a null.");
		}
		
		if(StringUtil.isNullOrEmpty(mailMessage.getRecipients())) {
			throw new IllegalArgumentException("O destinatário do e-mail é obrigatório.");
		}
		
		if(StringUtil.isNullOrEmpty(mailMessage.getText())) {
			throw new IllegalArgumentException("A mensagem do e-mail deve ser informada.");
		}
		
		if(StringUtil.isNullOrEmpty(mailMessage.getSubject())) {
			throw new IllegalArgumentException("O título do e-mail é obrigatório.");
		}
		
		Session session = Session.getInstance(getConfig(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("usuario", "senha");
			}
		});
		
//		session.setDebug(true);
		
		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("app.fiplangrf@mti.mt.gov.br"));

			Address[] toUser = InternetAddress.parse(mailMessage.getRecipients());
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(mailMessage.getSubject());
			
			if(StringUtil.isNullOrEmpty(mailMessage.getContent())) {
				message.setContent(mailMessage.getText(), "text/html");
			} else {
				message.setContent(mailMessage.getText(), mailMessage.getContent());
			}
			
			Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public Properties getConfig() {
		return config;
	}

	public void setConfig(Properties config) {
		this.config = config;
	}
}