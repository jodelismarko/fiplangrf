package br.gov.mt.mti.fiplangrf.model.tabelas;

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
import br.gov.mt.mti.fiplangrf.dominio.DominioNaturezaDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB013_ITEM_DESPESA_AUD")
@Table(name = "DHRTB013_ITEM_DESPESA")
@EqualsAndHashCode(callSuper = false , of = {"id", "descricaoItemDespesa"})
@ToString(callSuper = false, of = {"id", "descricaoItemDespesa", "flagNaturezaDespesa", "flagSituacao"})
public class ItemDespesa extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -6415462671252585757L;
	
	@Id
	@Column(name = "IDEN_ITEM_DESPESA", length = 8)
	@SequenceGenerator(name = "ITEM_DESPESA_SEQ", sequenceName = "DHRSQ013_ITEM_DESPESA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_DESPESA_SEQ")
	private Long id;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_ITEM_DESPESA", length = 100, nullable = false)
	private String descricaoItemDespesa;
	
	@Column(name = "FLAG_NATUREZA_DESPESA", length = 7, columnDefinition = "VARCHAR2(3)", nullable = false )
	@Type(type = DominioNaturezaDespesa.NOME)
	private DominioNaturezaDespesa flagNaturezaDespesa;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;
	
	@NotAudited
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_ITEM_DESPESA", nullable = true,  insertable = false, updatable = false, foreignKey = @ForeignKey(name = "DHRFK013_DHRTB011_GP_CTRL_DESP"))
	private GrupoControleDespesa grupoControleDespesa;
	
	
}
