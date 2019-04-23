package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB014_PLAN_ANUAL_PRAZO")
@Table(name = "DHRTB014_PLAN_ANUAL_PRAZO")
@EqualsAndHashCode(callSuper = false , of = {"id", "numeroExercicio"})
@ToString(callSuper = false, of = {"id", "numeroExercicio", "dataInicioEstimativa", "dataFimEstimativa", "flagSituacao"})
public class PlanejamentoAnualPrazos extends BaseEntity<Long> {

	private static final long serialVersionUID = 2569530138113062548L;

	@Id
	@Column(name = "IDEN_PLAN_ANUAL_PRAZO", length = 8)
	@SequenceGenerator(name = "PLAN_ANUAL_PRAZO_SEQ", sequenceName = "DHRSQ014_PLAN_ANUAL_PRAZO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAN_ANUAL_PRAZO_SEQ")
	private Long id;
	
	@Column(name = "NUMR_EXERCICIO", length = 4, nullable = false)
	private Integer numeroExercicio;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)", nullable = false )
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao;
	
	@Column(name="DATA_INICIO_ESTIMATIVA", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioEstimativa;
	
	@Column(name="DATA_FIM_ESTIMATIVA", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimEstimativa;
	
	@NotAudited
	@OneToMany( mappedBy = "planAnualPrazo", fetch = FetchType.LAZY )
	private Set<PrazoSolicitacaoMensal> prazoSolRepMensal = new HashSet<PrazoSolicitacaoMensal>();
	
}
