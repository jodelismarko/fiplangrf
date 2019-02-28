package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigDecimal;
import java.math.BigInteger;
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

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoDocumentoRF;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable(value = "DHUTB020_DOC_FINANC_AUD")
@Table(name = "DHUTB020_DOC_FINANC")
@EqualsAndHashCode(callSuper = false)
public class DocumentoFinanceiro extends BaseEntity<Long> {

	private static final long serialVersionUID = 7332808429030517067L;
	
	@Id
	@Column(name = "IDEN_DOC_FINANCEIRO", length = 8)
	@SequenceGenerator(name = "DOC_FINANC_SEQ", sequenceName = "DHUSQ020_DOC_FINANC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOC_FINANC_SEQ")
	private Long id;
	
	@Column(name = "TIPO_DOCUMENTO", length = 50, nullable = false)
	@Type(type = DominioTipoDocumentoRF.NOME)
	private DominioTipoDocumentoRF tipoDocumento;

	@Column(name = "NUMR_DOCUMENTO", length = 20, nullable = false)
	private BigInteger numeroDocumento;
	
	@Column(name = "VALR_DOCUMENTO", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorDocumento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_DOCUMENTO", nullable = false)
	private Date dataDocumento = new Date();
	
	@Column(name = "SITU_DOCUMENTO", length = 9, nullable = false)
	@Type(type = DominioSituacaoDoc.NOME)
	private DominioSituacaoDoc flagSituacaoDocumento = DominioSituacaoDoc.NORMAL;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "DHUFK020_DHUTB007_USUARIO"))
	private Usuario usuario;
	
	@ManyToOne( optional = true, fetch = FetchType.LAZY )
	@JoinColumn( name = "IDEN_DETALHAMENTO", nullable = false, foreignKey = @ForeignKey( name = "DHUFK020_DHU020_DETALHAMENTO" ) )
	private Detalhamento detalhamento;
	
	@Transient
    private Integer fakeOrder = 1;

	@Override
	public String toString() {
		return "DocumentoFinanceiro [id=" + id + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", valorDocumento=" + valorDocumento + ", dataDocumento=" + dataDocumento + "]";
	}
	
	
}
