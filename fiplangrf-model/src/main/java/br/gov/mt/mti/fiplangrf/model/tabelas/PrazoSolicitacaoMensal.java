package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB015_PRAZO_SOL_REP_MENSAL")
@Table(name = "DHRTB015_PRAZO_SOL_REP_MENSAL")
@EqualsAndHashCode(callSuper = false , of = {"id", "flagMes","dataInicioSolic","dataFimSolic" })
@ToString(callSuper = false, of = {"id", "flagMes", "dataInicioSolic", "dataFimSolic"})
public class PrazoSolicitacaoMensal extends BaseEntity<Long> {
	
	private static final long serialVersionUID = -5336207809435953655L;

	@Id
	@Column(name = "IDEN_PRAZO_SOL_REP_MENSAL", length = 8)
	@SequenceGenerator(name = "PRAZO_SOL_REP_MENSAL_SEQ", sequenceName = "DHRSQ015_PRAZO_SOL_REP_MENSAL", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRAZO_SOL_REP_MENSAL_SEQ")
	private Long id;
	
	@Column(name = "FLAG_MES", length = 9, nullable = false)
	@Type(type = DominioMes.NOME)
	private DominioMes flagMes;
	
	@Column(name="DATA_INICIO_SOLIC", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioSolic;
	
	@Column(name="DATA_FIM_SOLIC", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimSolic;
	
	@NotAudited
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_PLAN_ANUAL_PRAZO", nullable = true, foreignKey = @ForeignKey(name = "DHRFK015_DHRTB014_PLAN_ANUAL_PRAZO"))
	private PlanoAnualPrazo planAnualPrazo;
	
}
