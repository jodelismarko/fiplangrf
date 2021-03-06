package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioIndicativoProvisao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB010_DESPESA_AUD")
@Table(name = "DHRTB010_DESPESA")
@EqualsAndHashCode(callSuper = false , of = {"id", "descricaoDespesa"})
@ToString(callSuper = false, of = {"id", "descricaoDespesa", "flagIndicativoProvisao", "flagSituacao"})
public class Despesa extends  BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -4028699455390951725L;
	
	@Id
	@Column(name = "IDEN_DESPESA", length = 8)
	@SequenceGenerator(name = "DESPESA_SEQ", sequenceName = "DHRSQ010_DESPESA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESPESA_SEQ")
	private Long id;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_DESPESA", length = 50, nullable = false)
	private String descricaoDespesa;
	
	@GeneratorFieldOptions(defaultLabel = "Indicativo Provisão", filterable = true)
	@Column(name = "FLAG_INDICATIVO_PROVISAO", length = 7, columnDefinition = "VARCHAR2(3)", nullable = false )
	@Type(type = DominioIndicativoProvisao.NOME)
	private DominioIndicativoProvisao flagIndicativoProvisao;
	
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;
	
	@NotAudited
	@OneToMany(mappedBy = "despesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<DetalhamentoProvisaoDespesa> listaDetProvisaoDespesa = new HashSet<>();

	@NotAudited
	@OneToMany( mappedBy = "despesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<GrupoControleDespesa> listaGrupoControle = new HashSet<>();


	

}
