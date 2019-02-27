package br.gov.mt.mti.fiplangrf.common.security.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(of= {"codigo","nome"})
public class UsuarioLogado implements Serializable {
	
	private static final long serialVersionUID = 5379283644478826485L;
	public static final String USUARIO_LOGADO_KEY = "usuarioLogado";
	
	private Long codigo;
	private String nome;
	private String nomeSobrenome;
	private Date dataValidade;
	/* Adicionados por conta da autidoria - Envers */
	private String ip;
	private String cpf;
	
	private String action;	
	private byte[] imagemPerfil;
	
	private Date sessionCreatedTime;
	private String sessionId;
	private boolean expired;
}
