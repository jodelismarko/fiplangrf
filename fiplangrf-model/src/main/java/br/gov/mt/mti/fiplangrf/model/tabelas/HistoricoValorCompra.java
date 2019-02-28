package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable(value = "DHUTB018_HIST_VALR_COMP_AUD")
@Table(name = "DHUTB018_HIST_VALR_COMP")
@EqualsAndHashCode(callSuper = false)
public class HistoricoValorCompra extends BaseEntity<Long> implements Comparable<HistoricoValorCompra> {

	private static final long serialVersionUID = 1628826645600804917L;

	@Id
	@Column(name = "IDEN_HIST_VALR_COMP", length = 8)
	@SequenceGenerator(name = "HIST_VALR_COM_SEQ", sequenceName = "DHUSQ018_HIST_VALR_COM", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIST_VALR_COM_SEQ")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_VALR_COMPLEMENTAR")
	private Date dataValorComplementar;
	
	@Column(name = "DESC_JUSTIFICATIVA", length = 500)
	private String justificativa;

	@Column(name = "VALR_COMPLEMENTAR_HIST", precision = 15, scale = 2)
	private BigDecimal valorComplementar;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_USUARIO", nullable = true, foreignKey = @ForeignKey(name = "DHUFK018_DHUTB007_USUARIO"))
	private Usuario usuario;
	
	/*
	 * OneToMany
	 */
	

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_RECURSO_FINC", nullable = true, foreignKey = @ForeignKey(name = "DHUFK018_DHUTB017_RECURSO_FINC"))
	private RecursoFinanceiro recursoFinanceiro;
	
	@Override
	public int compareTo(HistoricoValorCompra arg0) {
		return this.getDataValorComplementar().compareTo(arg0.getDataValorComplementar());
	}
	
	
	@Transient
	private Integer fonteGrupo;
	
	@Transient
	private Integer tempId = 0;
	
	public boolean igual(Object obj) {
		HistoricoValorCompra other = (HistoricoValorCompra) obj;
		if (this.getId() != null) {
			if (this.getId().equals(other.getId())) {
				return true;
			} else {
				return false;
			}
		}

		if (this.tempId == other.getTempId()) {
			return true;
		}

		return false;
	}

	
}
