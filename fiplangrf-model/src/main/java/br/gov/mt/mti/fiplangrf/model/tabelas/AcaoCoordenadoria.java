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
@AuditTable("DHWTB012_ACAO_COORDEN_AUD")
@Table(name = "DHWTB012_ACAO_COORDENADORIA")
@ToString(callSuper = false, exclude = { "anexos" })
@EqualsAndHashCode(callSuper = false, exclude = { "anexos" })
@GeneratorEntityOptions(defaultLabel = "Ação Coordenadoria")
public class AcaoCoordenadoria extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 753519729227673985L;

	@Id
	@Column(name = "IDEN_ACAO_COORDENADORIA")
	@SequenceGenerator(name = "ACAO_COORDENADORIA_SEQ", sequenceName = "DHWSQ012_ACAO_COORDENADORIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_COORDENADORIA_SEQ")
	private Long id;

	@Column(name = "DESC_ACAO_COORDENADORIA", length = 2000)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK012_DHWTB011_OCORRENCIA"))
	private Ocorrencia ocorrencia;

	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHWTB021_ACAO_COORDEN_ANEXO", joinColumns = {
			@JoinColumn(name = "IDEN_ACAO_COORDENADORIA") }, inverseJoinColumns = {
					@JoinColumn(name = "IDEN_ANEXO") }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK021_DHWTB012_ACAO_COORDEN"), inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "DHWFK021_DHWTB019_ANEXO"))
	private Set<Anexo> anexos = new HashSet<Anexo>();

	public AcaoCoordenadoria() {
		setDataCriacao(new Date());
	}
}
