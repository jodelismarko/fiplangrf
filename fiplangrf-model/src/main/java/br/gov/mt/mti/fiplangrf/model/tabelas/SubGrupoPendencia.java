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
import org.hibernate.envers.AuditTable;
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
@AuditTable("DHWTB003_SUBGRUPO_PEND_AUD")
@Table(name = "DHWTB003_SUBGRUPO_PENDENCIA")
@EqualsAndHashCode(callSuper = false, of = { "id", "descricao", "situacao" })
@ToString(callSuper = false, exclude = { "grupoPendencia" })
@GeneratorEntityOptions(defaultLabel = "Ocorrencia", descriptionProperty = "descricao")
public class SubGrupoPendencia extends BaseVersionedEntity<Long> implements Comparable<SubGrupoPendencia> {

	private static final long serialVersionUID = 753519729227673985L;

	@Id
	@Column(name = "IDEN_SUBGRUPO_PENDENCIA")
	@SequenceGenerator(name = "SUBGRUPO_PENDENCIA_SEQ", sequenceName = "DHWSQ003_SUBGRUPO_PENDENCIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBGRUPO_PENDENCIA_SEQ")
	private Long id;

	@Column(name = "DESC_SUBGRUPO_PENDENCIA", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_GRUPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK003_DHWTB002_GRUPO_PENDEN"))
	private GrupoPendencia grupoPendencia;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@Override
	public int compareTo(SubGrupoPendencia o) {
		return this.getDescricao().compareToIgnoreCase(o.getDescricao());
	}
}
