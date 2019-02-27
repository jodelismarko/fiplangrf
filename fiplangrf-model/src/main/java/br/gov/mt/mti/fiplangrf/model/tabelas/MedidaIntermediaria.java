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
@Data
@Audited
@AuditTable("DHWTB013_MED_INTERMEDIARIA_AUD")
@Table(name = "DHWTB013_MEDIDA_INTERMEDIARIA")
@ToString(callSuper = false, exclude = { "anexos" })
@EqualsAndHashCode(callSuper = false, exclude = { "anexos" })
@GeneratorEntityOptions(defaultLabel = "Unidade Administrativa", descriptionProperty = "descricao")
public class MedidaIntermediaria extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 4783536118043116767L;

	@Id
	@Column(name = "IDEN_MEDIDA_INTERMEDIARIA")
	@SequenceGenerator(name = "MEDIDA_INTERMEDIARIA_SEQ", sequenceName = "DHWSQ013_MEDIDA_INTERMEDIARIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDIDA_INTERMEDIARIA_SEQ")
	private Long id;

	@Column(name = "DESC_MEDIDA_INTERMEDIARIA", length = 2000)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@Column(name = "NOME_RESPONSAVEL", length = 50)
	@GeneratorFieldOptions(defaultLabel = "Nome Responsavel", filterable = true)
	private String nomeResponsavel;

	@Column(name = "DESC_CARGO_FUNCAO", length = 50)
	@GeneratorFieldOptions(defaultLabel = "Cargo/Função", filterable = true)
	private String cargoFuncao;

	@Column(name = "NUMR_TELEFONE", length = 20)
	@GeneratorFieldOptions(defaultLabel = "Número Telefone", filterable = true)
	private String numrTelefone;

	@Column(name = "DESC_EMAIL", length = 50)
	@GeneratorFieldOptions(defaultLabel = "Email", filterable = true)
	private String email;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK013_DHWTB011_OCORRENCIA"))
	private Ocorrencia ocorrencia;

	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHWTB022_MEDIDA_INTERMED_ANEXO",
			joinColumns = { @JoinColumn(name = "IDEN_MEDIDA_INTERMEDIARIA")},
			inverseJoinColumns = { @JoinColumn(name = "IDEN_ANEXO")},
			foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK022_DHWTB013_MED_ITERMEDIA"),
			inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK022_DHWTB019_ANEXO"))
	private Set<Anexo> anexos = new HashSet<Anexo>();
	
	public MedidaIntermediaria() {
		setDataCriacao(new Date());
	}
}
