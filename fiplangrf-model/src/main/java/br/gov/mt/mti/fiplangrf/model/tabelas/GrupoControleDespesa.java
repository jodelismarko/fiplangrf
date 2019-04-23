package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTetoFinanceiroFiplan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB011_GP_CTRL_DESP")
@Table(name = "DHRTB011_GP_CTRL_DESP")
@EqualsAndHashCode(callSuper = false , of = {"id", "codigoGrupoControleDespesa"})
@ToString(callSuper = false, of = {"id", "codigoGrupoControleDespesa", "descricaoGrupoControleDespesa","flagTetoFinanceiroFiplan", "flagSituacao"})
public class GrupoControleDespesa extends BaseEntity<Long>{

	private static final long serialVersionUID = 576922170431686209L;
	
	@Id
	@Column(name = "IDEN_GP_CTRL_DESP", length = 8)
	@SequenceGenerator(name = "GP_CTRL_DESP_SEQ", sequenceName = "DHRSQ011_GP_CTRL_DESP", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_CTRL_DESP_SEQ")
	private Long id;
	
	@Column(name = "CODG_GP_CTRL_DESP", length = 8, precision = 3, nullable = false)
	private BigInteger codigoGrupoControleDespesa;
	
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESC_GP_CTRL_DESP", length = 50, nullable = false)
	private String descricaoGrupoControleDespesa;
	
	@Column(name = "FLAG_TETO_FINANCEIRO_FIPLAN", length = 7, columnDefinition = "VARCHAR2(3)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioTetoFinanceiroFiplan flagTetoFinanceiroFiplan;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;
	
	@NotAudited
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_DESPESA", nullable = true, foreignKey = @ForeignKey(name = "DHRFK011_DHRTB010_DESPESA"))
	private Despesa despesa;
	
	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHRTB016_DET_DESP_GP_CTRL_DESP", joinColumns = {
			@JoinColumn(name = "IDEN_GP_CTRL_DESP") }, inverseJoinColumns = { @JoinColumn(name = "IDEN_DET_DESPESA") })
	private Set<DetalhamentoDespesa> detalheDespesas = new HashSet<>();
	
	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHRTB012_FT_REC_GP_CTRL_DESP", joinColumns = {
			@JoinColumn(name = "IDEN_GP_CTRL_DESP") }, inverseJoinColumns = { @JoinColumn(name = "IDEN_FONTE_RECURSO") })
	private Set<FonteRecurso> fonteRecursos = new HashSet<>();

}
