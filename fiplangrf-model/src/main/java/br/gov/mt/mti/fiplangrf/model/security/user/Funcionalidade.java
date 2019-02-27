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
@Table(name = "DHWTB027_FUNCIONALIDADE")
@ToString(callSuper = false, of = { "id", "nome", "situacao" })
@EqualsAndHashCode(callSuper = false, of = { "id", "nome", "situacao" })
@GeneratorEntityOptions(defaultLabel = "Funcionalidade", descriptionProperty = "nome")
public class Funcionalidade extends BaseVersionedEntity<Long> implements Comparable<Funcionalidade>{

	private static final long serialVersionUID = -3255164887046820023L;

	@Id
	@Column(name = "IDEN_FUNCIONALIDADE", length = 10)
	@SequenceGenerator(name = "FUNCIONALIDADE_SEQUENCE", sequenceName = "DHWSQ027_FUNCIONALIDADE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONALIDADE_SEQUENCE")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "nome", filterable = true)
	@Column(name = "NOME", length = 200, nullable = false, unique = true)
	private String nome;

	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESCRICAO", length = 400, nullable = true)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Type(type = DominioSituacao.NOME)
	@Column(name = "FLAG_SITUACAO")
	private DominioSituacao situacao;

	@ManyToMany
	@JoinTable(name = "DHWTB026_FUNC_PERMISSAO", joinColumns = {
			@JoinColumn(name = "IDEN_FUNCIONALIDADE") }, inverseJoinColumns = {
					@JoinColumn(name = "IDEN_PERMISSAO") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK026_DHWTB027_FUNCIONALIDA"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK026_DHWTB028_PERMISSAO"))
	private Set<Permissao> permissoes = new HashSet<>();

	public Funcionalidade() {
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

	@Override
	public int compareTo(Funcionalidade o) {
		return this.getDescricao().compareTo(o.getDescricao());
	}
}