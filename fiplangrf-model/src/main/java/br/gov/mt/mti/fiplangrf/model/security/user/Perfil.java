package br.gov.mt.mti.fiplangrf.model.security.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Audited
@Data
@Table(name = "DHWTB017_PERFIL_ACESSO")
@ToString(callSuper = false, of = { "id", "descricao", "situacao" })
@EqualsAndHashCode(callSuper = false, of = { "id", "descricao", "situacao" })
@GeneratorEntityOptions(defaultLabel = "Perfil", descriptionProperty = "descricao")
public class Perfil extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -3255164887046820023L;

	@Id
	@Column(name = "IDEN_PERFIL_ACESSO", length = 10)
	@SequenceGenerator(name = "PERFIL_ACESSO_SEQ", sequenceName = "DHWSQ017_PERFIL_ACESSO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PERFIL_ACESSO_SEQ")
	private Long id;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable=true)
	@Column(name = "DESC_PERFIL_ACESSO", length=200, nullable=false, unique=true) 
	private String descricao;
	
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToMany
	@JoinTable(name = "DHWTB024_PERFIL_FUNC", 
			joinColumns = { @JoinColumn(name = "IDEN_PERFIL_ACESSO")},
			inverseJoinColumns = { @JoinColumn(name = "IDEN_FUNCIONALIDADE")},
			foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK024_DHWTB017_PERFIL_ACESS"),
			inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK024_DHWTB027_FUNCIONALIDA"))
	private Set<Funcionalidade> funcionalidades = new HashSet<>();

	public Perfil() {
		super();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
