package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Data
@Audited
@Table(name = "DHWTB004_TIPO_PENDENCIA")
@EqualsAndHashCode(callSuper = false, exclude = { "tipoOcorrencia", "grupoPendencia", "subGrupoPendencia" })
@ToString(callSuper = false, exclude = { "tipoOcorrencia", "grupoPendencia", "subGrupoPendencia" })
@GeneratorEntityOptions(defaultLabel = "Tipo Pendencia", descriptionProperty = "descricao")
public class TipoPendencia extends BaseVersionedEntity<Long> implements Comparable<TipoPendencia> {

	private static final long serialVersionUID = 599025143273362023L;

	@Id
	@Column(name = "IDEN_TIPO_PENDENCIA")
	@SequenceGenerator(name = "TIPO_PENDENCIA_SEQ", sequenceName = "DHWSQ004_TIPO_PENDENCIA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TIPO_PENDENCIA_SEQ")
	private Long id;

	@Column(name = "SIGLA_TIPO_PENDENCIA", length = 100)
	@GeneratorFieldOptions(defaultLabel = "Sigla", filterable = true)
	private String sigla;

	@Column(name = "DESC_TIPO_PENDENCIA", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_TIPO_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(name = "DHWFK004_DHWTB001_TIPO_OCORREN"))
	private TipoOcorrencia tipoOcorrencia;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_GRUPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK004_DHWTB002_GRUPO_PENDEN"))
	private GrupoPendencia grupoPendencia;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_SUBGRUPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK004_DHWTB003_SUBGRUPO_PEN"))
	private SubGrupoPendencia subGrupoPendencia;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@Override
	public int compareTo(TipoPendencia o) {
		return this.descricao.compareToIgnoreCase(o.descricao);
	}
}
