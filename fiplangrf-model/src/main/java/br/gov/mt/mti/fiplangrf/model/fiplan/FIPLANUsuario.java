package br.gov.mt.mti.fiplangrf.model.fiplan;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.gov.mt.mti.fiplangrf.dominio.DominioSimNaoFIPLAN;
import lombok.Data;

@Data
@Entity
@Table(name = "ACWVW0464")
public class FIPLANUsuario implements Serializable {

	private static final long serialVersionUID = 220827484903908009L;

	@Id
	@Column(name = "NR_CPF", insertable = false, updatable = false)
	private String login;

	@Column(name = "DS_NOME", insertable = false, updatable = false)
	private String nome;

	@Column(name = "DS_EMAIL", insertable = false, updatable = false)
	private String email;

	@Column(name = "NR_TEL_COM", insertable = false, updatable = false)
	private String telefoneFixo;

	@Column(name = "NR_TEL_CEL", insertable = false, updatable = false)
	private String telefoneCelular;

	@Column(name = "DT_EXPIRACAO", insertable = false, updatable = false)
	private Date dataExpiracao;

	@Column(name = "FLG_USR_BLOQUEADO", insertable = false, updatable = false)
	@Type(type = DominioSimNaoFIPLAN.NOME)
	private DominioSimNaoFIPLAN flagBloqueado;

}