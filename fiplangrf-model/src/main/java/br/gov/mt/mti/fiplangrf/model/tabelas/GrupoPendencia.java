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
@Table(name = "DHWTB002_GRUPO_PENDENCIA")
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(callSuper = false, exclude = { "tipoOcorrencia" })
@GeneratorEntityOptions(defaultLabel = "Grupo Pendencia", descriptionProperty = "descricao")
public class GrupoPendencia extends BaseVersionedEntity<Long> implements Comparable<GrupoPendencia> {

	private static final long serialVersionUID = 753519729227673985L;

	@Id
	@Column(name = "IDEN_GRUPO_PENDENCIA")
	@SequenceGenerator(name = "GRUPO_PENDENCIA_SEQ", sequenceName = "DHWSQ002_GRUPO_PENDENCIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPO_PENDENCIA_SEQ")
	private Long id;

	@Column(name = "DESC_GRUPO_PENDENCIA", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_TIPO_OCORRENCIA", foreignKey = @ForeignKey(name = "DHWFK002_DHWTB001_TIPO_OCORREN"))
	private TipoOcorrencia tipoOcorrencia;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	
	@Override
	public int compareTo(GrupoPendencia o) {
		return this.getDescricao().compareToIgnoreCase(o.getDescricao());
	}
	
}
