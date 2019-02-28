
package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioGrupoControleTF;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoDocumentoRF;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacaoNLA;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable(value = "DHUTB019_DETALHAMENTO_AUD")
@Table(name = "DHUTB019_DETALHAMENTO")
@EqualsAndHashCode(callSuper = false)
public class Detalhamento extends BaseEntity<Long> implements Comparable<Detalhamento> {

	private static final long serialVersionUID = -6685000083925330226L;

	@Id
	@Column(name = "IDEN_DETALHAMENTO", length = 8)
	@SequenceGenerator(name = "DETALHAMENTO_SEQ", sequenceName = "DHUSQ019_DETALHAMENTO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALHAMENTO_SEQ")
	private Long id;

	@Column(name = "VALR_DISPONIVEL_DETALHAR", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorDisponivel;

	@Column(name = "VALR_DETALHADO", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorDetalhado = BigDecimal.ZERO;
	
	@Column(name = "VARL_NLA", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorNLA = BigDecimal.ZERO;

	@Column(name = "FLAG_LIMITE_EMPRESTIMO", length = 8, columnDefinition = "VARCHAR2(8)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagLimiteEmprestimo = DominioSimNao.NAO;

	@Column(name = "NUMR_NLA", length = 19)
	private Long numeroNLA;

	@Column(name = "FLAG_RESTOS_PAGAR", length = 8, columnDefinition = "VARCHAR2(8)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagRestoPagar;

	@Column(name = "GRPO_CONTROLE_TETO", length = 50, nullable = true)
	@Type(type = DominioGrupoControleTF.NOME)
	private DominioGrupoControleTF flagGrupoControleTeto;

	@Column(name = "VALR_REPASSAR", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorRepassar;

	@Column(name = "TIPO_OPERACAO_NLA", length = 5, columnDefinition = "VARCHAR2(5)")
	@Type(type = DominioTipoOperacaoNLA.NOME)
	private DominioTipoOperacaoNLA flagOperacaoNLA = DominioTipoOperacaoNLA.INCORPORACAO;
	
	@Column(name = "FLAG_NLA", length = 9, columnDefinition = "VARCHAR2(9)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagNLA = DominioSimNao.NAO;

	@Column(name = "FLAG_ARR", length = 9, columnDefinition = "VARCHAR2(9)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagARR = DominioSimNao.NAO;

	@Column(name = "FLAG_ARR_DEVOLUCAO", length = 9, columnDefinition = "VARCHAR2(9)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagARRDevolucao = DominioSimNao.NAO;

	@Column(name = "FLAG_REPASSE_COM_ONUS", length = 9, columnDefinition = "VARCHAR2(9)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagRepasseRecebido = DominioSimNao.NAO;

	@Column(name = "FLAG_REPASSE_SEM_ONUS", length = 9, columnDefinition = "VARCHAR2(9)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao flagRepasseConcedido = DominioSimNao.NAO;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_USUARIO", nullable = true, foreignKey = @ForeignKey(name = "DHUFK019_DHUTB007_USUARIO"))
	private Usuario usuario;

	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)")
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao = DominioSituacaoRegistro.ATIVO;
	
	@Transient
	private BigDecimal valorDetalhadoOLd = BigDecimal.ZERO;
	
	@Transient
	private List<BigDecimal> valorDetalhadoOldList = new ArrayList<>();

	public void setValorDetalhadoOLd(BigDecimal valorDetalhadoOLd) {
		if (valorDetalhadoOLd != null) {
			valorDetalhadoOldList.add(valorDetalhadoOLd);
			this.valorDetalhadoOLd = valorDetalhadoOLd;
		}
	}
	
	/*
	 * ManyToOne
	 */

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDEN_RECURSO_FINC", nullable = true, foreignKey = @ForeignKey(name = "DHUFK019_DHUTB017_RECURSO_FINC"))
	private RecursoFinanceiro recursoFinanceiro;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "IDEN_FONTE_RECURSO", nullable = true, foreignKey = @ForeignKey(name = "DHUFK019_DHUTB021_FONTE_REC"))
	private FonteRecurso fonteRecurso;

	/*
	 * OneToMany
	 */

	@NotAudited
	@OneToMany( mappedBy = "detalhamento", fetch = FetchType.LAZY )
	private Set<HistoricoValorDetalhamento> historicoValorDetalhamentos = new HashSet<HistoricoValorDetalhamento>();

	@OneToMany( mappedBy = "detalhamento", fetch = FetchType.LAZY, cascade = CascadeType.DETACH, orphanRemoval = true )
	private Set<DocumentoFinanceiro> documentoFinanceiros = new HashSet<DocumentoFinanceiro>();
	@Transient
	private List<DocumentoFinanceiro> documentoFinanceiroList = new ArrayList<DocumentoFinanceiro>();

	//Calcula o valor a repassar de ARR
	public BigDecimal getValorRepassar() {
		BigDecimal valorTotalRepassar = this.getValorDetalhadoOldList().stream().findFirst().orElse(BigDecimal.ZERO)
				.add(this.getTotalARRDevolucao())
				.subtract(this.getTotalARR())
				.subtract(this.getTotalOnusRecebido());

		return valorTotalRepassar;
	}
	
	
	//Calcula o valor Repassado
		public BigDecimal getValorRepassado() {
			BigDecimal valorTotalRepassado = this.getTotalARR()
				.add(this.getTotalOnusRecebido())
				.subtract(this.getTotalARRDevolucao());

			return valorTotalRepassado;
		}

	/**
	 * @return
	 * Regra para exclusão de Documento de devolução
	 */
	public BigDecimal getValorRepassarDevolucao() {
		BigDecimal valorTotalRepassar = this.getValorDetalhadoOLd()
				.add(this.getTotalARRDevolucao())
				.subtract(this.getTotalARR());

		return valorTotalRepassar;
	}

	public BigDecimal getTotalARR() {
		BigDecimal valorTotalARR = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getTipoDocumento().equals(DominioTipoDocumentoRF.ARR) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return valorTotalARR;
	}

	public BigDecimal getTotalARRDevolucao() {
		BigDecimal valorTotalARRDevolucao = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getTipoDocumento().equals(DominioTipoDocumentoRF.ARR_DEVOLUCAO) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return valorTotalARRDevolucao;
	}
	
	public BigDecimal getTotalIncorporacao() {
		BigDecimal valorTotalIncorporacao = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getTipoDocumento().equals(DominioTipoDocumentoRF.INCORPORACAO_OUTROS_FATOS_CONTABEIS) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return valorTotalIncorporacao;
	}
	
	public BigDecimal getTotalDesincorporacao() {
		BigDecimal valorTotalDesincorporacao = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getTipoDocumento().equals(DominioTipoDocumentoRF.DESINCORPORACAO_OUTROS_FATOS_CONTABEIS) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return valorTotalDesincorporacao;
	}
	
	public BigDecimal getTotalOnusRecebido() {
		BigDecimal valorTotalOnusRecebido = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getValorDocumento() != null && d.getTipoDocumento().equals(DominioTipoDocumentoRF.REPASSE_ONUS_RECEBIBO) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		return valorTotalOnusRecebido;
	}
	
	public BigDecimal getTotalOnusConcedido() {
		BigDecimal valorTotalOnusConcedido = this.getDocumentoFinanceiroList().stream()
				.filter(d -> d.getValorDocumento() != null && d.getTipoDocumento().equals(DominioTipoDocumentoRF.REPASSE_ONUS_CONCEDIDO) && d.getFlagSituacaoDocumento().equals(DominioSituacaoDoc.NORMAL))
				.map(doc -> doc.getValorDocumento()).reduce(BigDecimal.ZERO, BigDecimal::add);
		return valorTotalOnusConcedido;
	}
	
	public BigDecimal getDiferencaDetalhar() {
		
		if(this.flagNLA.equals(DominioSimNao.SIM)) {
			if (this.getValorDetalhado().compareTo(this.getValorDetalhadoOldList().stream().findFirst().orElse(BigDecimal.ZERO)) > 0) {
			    	this.setFlagOperacaoNLA(DominioTipoOperacaoNLA.INCORPORACAO);
				return getValorDetalhado().subtract(this.getValorDetalhadoOldList().stream().findFirst().orElse(BigDecimal.ZERO));
			}
	
			if (this.getValorDetalhado().compareTo(this.getValorDetalhadoOldList().stream().findFirst().orElse(BigDecimal.ZERO)) < 0) {
				this.setFlagOperacaoNLA(DominioTipoOperacaoNLA.DESINCORPORACAO);
				return this.getValorDetalhadoOldList().stream().findFirst().orElse(BigDecimal.ZERO).subtract(this.getValorDetalhado());
			}		
		}
		
		return this.getValorDetalhado();
	}
	
	@Transient
	private Integer numeroFonteGrupo;
	
	public Integer getNumeroFonteGrupo() {
		return this.getFonteRecurso().getCodgFonteRecurso() + (this.getFlagGrupoControleTeto().getCod()*1000) + (this.getTempId());
	}
	
	public boolean isValorDetalhadoDiferente() {
		return !this.getValorDetalhado().equals(this.getValorDetalhadoOldList().stream().findFirst().orElse(this.getValorDetalhado()));
	}
	
	
	@Transient
	private Integer tempId = 0;

	public boolean igual(Object obj) {
		Detalhamento other = (Detalhamento) obj;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalhamento other = (Detalhamento) obj;
		if (flagARR != other.flagARR)
			return false;
		if (flagARRDevolucao != other.flagARRDevolucao)
			return false;
		if (flagLimiteEmprestimo != other.flagLimiteEmprestimo)
			return false;
		if (flagNLA != other.flagNLA)
			return false;
		if (flagRepasseConcedido != other.flagRepasseConcedido)
			return false;
		if (flagRepasseRecebido != other.flagRepasseRecebido)
			return false;
		if (flagRestoPagar != other.flagRestoPagar)
			return false;
		if (flagGrupoControleTeto == null) {
			if (other.flagGrupoControleTeto != null)
				return false;
		} else if (!flagGrupoControleTeto.equals(other.flagGrupoControleTeto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroNLA == null) {
			if (other.numeroNLA != null)
				return false;
		} else if (!numeroNLA.equals(other.numeroNLA))
			return false;
		if (numeroFonteGrupo == null) {
			if (other.numeroFonteGrupo != null)
				return false;
		} else if (!numeroFonteGrupo.equals(other.numeroFonteGrupo))
			return false;
		if (valorDetalhado == null) {
			if (other.valorDetalhado != null)
				return false;
		} else if (!valorDetalhado.equals(other.valorDetalhado))
			return false;
		if (valorDisponivel == null) {
			if (other.valorDisponivel != null)
				return false;
		} else if (!valorDisponivel.equals(other.valorDisponivel))
			return false;
		if (valorRepassar == null) {
			if (other.valorRepassar != null)
				return false;
		} else if (!valorRepassar.equals(other.valorRepassar))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((flagARR == null) ? 0 : flagARR.hashCode());
		result = prime * result + ((flagARRDevolucao == null) ? 0 : flagARRDevolucao.hashCode());
		result = prime * result + ((flagLimiteEmprestimo == null) ? 0 : flagLimiteEmprestimo.hashCode());
		result = prime * result + ((flagNLA == null) ? 0 : flagNLA.hashCode());
		result = prime * result + ((flagRepasseConcedido == null) ? 0 : flagRepasseConcedido.hashCode());
		result = prime * result + ((flagRepasseRecebido == null) ? 0 : flagRepasseRecebido.hashCode());
		result = prime * result + ((flagRestoPagar == null) ? 0 : flagRestoPagar.hashCode());
		result = prime * result + ((flagGrupoControleTeto == null) ? 0 : flagGrupoControleTeto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroNLA == null) ? 0 : numeroNLA.hashCode());
		result = prime * result + ((valorDetalhado == null) ? 0 : valorDetalhado.hashCode());
		result = prime * result + ((valorDisponivel == null) ? 0 : valorDisponivel.hashCode());
		result = prime * result + ((valorRepassar == null) ? 0 : valorRepassar.hashCode());
		result = prime * result + ((numeroFonteGrupo == null) ? 0 : numeroFonteGrupo.hashCode());
		return result;
	}

	@Override
	public int compareTo(Detalhamento o) {
		return this.getDataCriacao().compareTo(o.getDataCriacao());
	}

	@Override
	public String toString() {
	    return "Detalhamento [id=" + id + ", valorDisponivel=" + valorDisponivel + ", valorDetalhado=" + valorDetalhado + ", valorNLA=" + valorNLA + ", flagLimiteEmprestimo=" + flagLimiteEmprestimo + ", numeroNLA=" + numeroNLA
			    + ", flagRestoPagar=" + flagRestoPagar + ", flagGrupoControleTeto=" + flagGrupoControleTeto + ", valorRepassar=" + valorRepassar + ", flagOperacaoNLA=" + flagOperacaoNLA + ", flagNLA=" + flagNLA + ", flagARR=" + flagARR
			    + ", flagARRDevolucao=" + flagARRDevolucao + ", flagRepasseRecebido=" + flagRepasseRecebido + ", flagRepasseConcedido=" + flagRepasseConcedido + ", flagSituacao=" + flagSituacao + "]";
	}

	
	
}
