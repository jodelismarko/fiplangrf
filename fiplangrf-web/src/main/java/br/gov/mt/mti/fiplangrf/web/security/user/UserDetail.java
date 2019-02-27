package br.gov.mt.mti.fiplangrf.web.security.user;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5379283644478826485L;

	private Long codigo;
	private String nome;
	private String email;
	private Date dataValidade;

	/* Adicionados por conta da autidoria - Envers */
	private String ip;
	private String cpf;
	private String sessionId;

	private Map<String, String> permissaoItemMenu = new HashMap<String, String>();
	private Map<String, String> funcionalidades = new HashMap<String, String>();

	public UserDetail(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, String> getPermissaoItemMenu() {
		return permissaoItemMenu;
	}

	public void setPermissaoItemMenu(Map<String, String> permissaoItemMenu) {
		this.permissaoItemMenu = permissaoItemMenu;
	}

	public Map<String, String> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Map<String, String> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
