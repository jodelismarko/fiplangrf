package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

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
@AuditTable("DHWTB014_ENC_PENDENCIA_AUD")
@Table(name = "DHWTB014_ENCERRAMENTO_PENDENCI")
@ToString(callSuper = false, exclude = { "anexos" })
@EqualsAndHashCode(callSuper = false, exclude = { "anexos" })
@GeneratorEntityOptions(defaultLabel = "Encerramento Pendencia", descriptionProperty = "descricao")
public class EncerramentoPendencia extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 6415089934264456711L;

	@Id
	@Column(name = "IDEN_ENCERRAMENTO_PENDENCIA")
	@SequenceGenerator(name = "ENC_PENDENCIA_SEQ", sequenceName = "DHWSQ014_ENCERRAMENTO_PENDENCI", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENC_PENDENCIA_SEQ")
	private Long id;

	@Column(name = "DESC_ENCERRAMENTO_PENDENCIA")
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK014_DHWTB011_OCORRENCIA"))
	private Ocorrencia ocorrencia;

	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHWTB023_ENCERRAMEN_PEND_ANEXO",
			joinColumns = { @JoinColumn(name = "IDEN_ENCERRAMENTO_PENDENCIA")},
			inverseJoinColumns = { @JoinColumn(name = "IDEN_ANEXO")},
			foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK023_DHWTB014_ENCER_PENDEN"),
			inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK023_DHWTB019_ANEXO"))
	private Set<Anexo> anexos = new HashSet<Anexo>();

	public EncerramentoPendencia() {
		setDataCriacao(new Date());
	}
	
}
