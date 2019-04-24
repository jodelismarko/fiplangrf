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
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB009_DET_PROV_DESPESA_AUD")
@Table(name = "DHRTB009_DETAL_PROV_DESPESA")
@EqualsAndHashCode(callSuper = false , of = {"id", "descricaoDetalheProvisao"})
@ToString(callSuper = false, of = {"id", "descricaoDetalheProvisao", "flagSituacao"})
public class DetalhamentoProvisaoDespesa extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 4573553459092155003L;
	
	@Id
	@Column(name = "IDEN_DETAL_PROV_DESPESA", length = 8)
	@SequenceGenerator(name = "DETAL_PROV_DESPESA_SEQ", sequenceName = "DHRSQ009_DETAL_PROV_DESPESA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETAL_PROV_DESPESA_SEQ")
	private Long id;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_DETAL_PROVISAO", length = 100, nullable = false)
	private String descricaoDetalheProvisao;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;
	
	@NotAudited
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "IDEN_DESPESA",  foreignKey = @ForeignKey(name = "DHRFK009_DHRTB010_DESPESA"))
	private Despesa despesa;

}
