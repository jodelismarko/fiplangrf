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

import org.hibernate.annotations.Type;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacaoNLA;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "DHUTB023_HIST_VALOR_DET")
@EqualsAndHashCode(callSuper = false)
public class HistoricoValorDetalhamento extends BaseEntity<Long> implements Comparable<HistoricoValorDetalhamento> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDEN_HIST_VALR_DET", length = 8)
	@SequenceGenerator(name = "HIST_VALOR_DET_SEQ", sequenceName = "DHUSQ023_HIST_VALOR_DET", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "HIST_VALOR_DET_SEQ")
	private Long id;
	
	@Column(name="DATA_OPERACAO", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOperacao = new Date();

	@Column(name = "VALR_DETALHADO", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorDetalhado = BigDecimal.ZERO;
	
	@Column(name = "VALR_NLA", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorNLA = BigDecimal.ZERO;

	@Column(name = "TIPO_OPERACAO_NLA", length = 5, columnDefinition = "VARCHAR2(5)")
	@Type(type = DominioTipoOperacaoNLA.NOME)
	private DominioTipoOperacaoNLA flagOperacaoNLA = DominioTipoOperacaoNLA.INCORPORACAO;
	
	
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_USUARIO", nullable = true, foreignKey = @ForeignKey(name = "DHUFK023_DHUTB007_USUARIO"))
	private Usuario usuario;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_DETALHAMENTO", nullable = true, foreignKey = @ForeignKey(name = "DHUFK023_DHUTB019_DETALHAMENTO"))
	private Detalhamento detalhamento;


	public int compareTo(HistoricoValorDetalhamento o) {
		return this.getDataOperacao().compareTo(o.getDataOperacao());
	}
	
	public HistoricoValorDetalhamento carregaHistorico(Detalhamento detalhamento) {
	    setValorDetalhado(detalhamento.getValorDetalhado());
	    setValorNLA(detalhamento.getDiferencaDetalhar());
	    setFlagOperacaoNLA(detalhamento.getFlagOperacaoNLA());
	    setUsuario(detalhamento.getUsuario());
	    setDetalhamento(detalhamento);
	    return this;
	}

}
