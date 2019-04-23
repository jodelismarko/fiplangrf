package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB009_DETAL_PROV_DESPESA")
@Table(name = "DHRTB009_DETAL_PROV_DESPESA")
@EqualsAndHashCode(callSuper = false , of = {"id", "descricaoDetalheProvisao"})
@ToString(callSuper = false, of = {"id", "descricaoDetalheProvisao", "flagSituacao"})
public class DetalheProvisaoDespesa extends BaseEntity<Long> {

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
	@OneToMany( mappedBy = "despesa", fetch = FetchType.LAZY )
	private Set<Despesa> despesas = new HashSet<Despesa>();

}
