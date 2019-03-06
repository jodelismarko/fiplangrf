package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.common.util.formatter.Formatter;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNLA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Audited
@Table(name = "DHUTB027_NLA")
@ToString(callSuper = false, exclude = { "anexos"})
@EqualsAndHashCode(callSuper = false, of = {"id", "numeroNLA"})
public class NLA extends BaseEntity<Long>{

	private static final long serialVersionUID = -2101202923493786019L;

	@Id
	@Column(name = "IDEN_NLA", length = 8)
	@SequenceGenerator(name = "NLA_SEQ", sequenceName = "DHUSQ027_NLA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "NLA_SEQ")
	private Long id;
	
	@Column(name = "FLAG_TIPO_NLA", length = 11)
	@Type(type = DominioTipoNLA.NOME)
	private DominioTipoNLA flagTipoCredor = DominioTipoNLA.PRINCIPAL;
	
	@Column(name = "NUMR_NLA", length = 18, nullable = false)
	private Long numeroNLA;

	@Column(name = "VALR_NLA", nullable = false)
	private BigDecimal valorNLA;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_NLA", nullable = false)
	private Date dataNLA;
	
	@Column(name = "DESC_MOTIVO_ERRO", length = 1000)
	private String descricaoMotivoErro;

	@Column(name = "NUMR_NLA_ESTORNO", length = 18)
	private Long numeroNLAEstorno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ESTORNO_NLA", nullable = false)
	private Date dataNLAEstorno;

	@Column(name = "FLAG_SITUACAO_NLA", length = 9)
	@Type(type = DominioSituacaoDoc.NOME)
	private DominioSituacaoDoc flagSituacaoNLA = DominioSituacaoDoc.NORMAL;
	
	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DHUTB033_NLA_ANEXO", joinColumns = {
			@JoinColumn(name = "IDEN_NLA") }, inverseJoinColumns = { @JoinColumn(name = "IDEN_ANEXO") })
	private Set<Anexo> anexos = new HashSet<>();

	public String getNumeroNLAFormatter() {
		return Formatter.formatter("#####.####.##.######-#", StringUtils.leftPad(String.valueOf(this.numeroNLA), 18, '0'));
	}


}
