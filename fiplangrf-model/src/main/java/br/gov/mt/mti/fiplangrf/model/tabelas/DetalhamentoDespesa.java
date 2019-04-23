package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB006_DET_DESPESA")
@Table(name = "DHRTB006_DET_DESPESA")
@EqualsAndHashCode(callSuper = false , of = {"id", "codigoDetDespesa"})
@ToString(callSuper = false, of = {"id", "codigoDetDespesa", "descricaoDetDespesa", "flagSituacao"})
public class DetalhamentoDespesa extends BaseEntity<Long> {
	
	private static final long serialVersionUID = -4735651967996160395L;
	
	@Id
	@Column(name = "IDEN_DET_DESPESA", length = 8)
	@SequenceGenerator(name = "DET_DESPESA_SEQ", sequenceName = "DHRSQ006_DET_DESPESA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DET_DESPESA_SEQ")
	private Long id;
	
	@Column(name = "CODG_DET_DESP", length = 2, nullable = false)
	private Integer codigoDetDespesa;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_DET_DESPESA", length = 100, nullable = false)
	private String descricaoDetDespesa;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;

}
