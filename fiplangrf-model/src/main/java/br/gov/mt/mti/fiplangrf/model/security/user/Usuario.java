package br.gov.mt.mti.fiplangrf.model.security.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.common.util.Sanityzer;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Audited
@Data
@Table(name = "DHWTB018_USUARIO")
@ToString(callSuper = false, of = { "codigo", "login", "situacao" })
@EqualsAndHashCode(callSuper = false, of = { "codigo", "login", "situacao" })
@GeneratorEntityOptions(defaultLabel = "Usuario", descriptionProperty = "nome")
public class Usuario extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -3255164887046820023L;

	@Id
	@Column(name = "IDEN_USUARIO")
	@SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "DHWSQ018_USUARIO", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	private Long codigo;

	@GeneratorFieldOptions(defaultLabel = "Login", filterable = true)
	@Column(name = "NUMR_CPF", length = 20, nullable = false, unique = true)
	private String login;

	@Column(name = "NUMR_CPF", length = 14, nullable = true, insertable = false, updatable = false)
	private String cpf;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinTable(name = "DHWTB025_USUARIO_PERFIL", 
		joinColumns = { @JoinColumn(name = "IDEN_USUARIO") }, 
		inverseJoinColumns = { @JoinColumn(name = "IDEN_PERFIL_ACESSO") }, 
		foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK025_DHWTB018_USUARIO"), 
		inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK025_DHWTB017_PERFIL_ACESS"))
	private Set<Perfil> perfis = new HashSet<>();

	@NotAudited
	@OneToOne
	@JoinColumn(name = "NUMR_CPF", referencedColumnName = "NR_CPF", nullable = true, insertable = false, updatable = false)
	private FIPLANUsuario fiplanUsuario;

	@GeneratorFieldOptions(defaultLabel = "Data de login", filterable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ULTIMO_ACESSO")
	private Date dataUltimoAcesso;

	@Lob
	@Basic
	@Column(name = "FOTO_PERFIL")
	private byte[] foto;

	public Usuario() {
		super();
	}

	@Override
	public Long getId() {
		return getCodigo();
	}

	@Override
	public void setId(Long id) {
		setCodigo(id);
	}

	public String getCpf() {
		if (StringUtils.isNotBlank(login))
			return StringUtils.formataCpf(new Long(login));
		return "";
	}

	public void setCpf(String cpf) {
		this.login = Sanityzer.sanityzeNumericString(cpf);
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String _cpf) {
		if (_cpf != null)
			_cpf = Sanityzer.sanityzeNumericString(_cpf);
		this.login = _cpf;
	}
}
