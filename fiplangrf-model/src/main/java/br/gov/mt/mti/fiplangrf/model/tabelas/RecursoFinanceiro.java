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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.joda.time.DateTime;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastroRF;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHUTB017_RECURSO_FINANCEIR_AUD")
@Table(name = "DHUTB017_RECURSO_FINANCEIRO")
@EqualsAndHashCode(callSuper = false , of = {"id", "execRecursoFinanceiro"})
@ToString(callSuper = false, of = {"id", "execRecursoFinanceiro", "mesRecursoFinanceiro", "valorProgramado", "flagStatus"})
public class RecursoFinanceiro extends BaseEntity<Long> {

	private static final long serialVersionUID = -6685000083925330226L;

	@Id
	@Column(name = "IDEN_RECURSO_FINC", length = 8)
	@SequenceGenerator(name = "RECURSO_FINANCEIRO_SEQ", sequenceName = "DHUSQ017_RECURSO_FINANCEIRO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECURSO_FINANCEIRO_SEQ")
	private Long id;

	@Column(name = "EXER_RECURSO_FINC", length = 4, nullable = false)
	private Integer execRecursoFinanceiro = new DateTime().getYear();

	@NotAudited
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns(value= {
			@JoinColumn( name = "CODG_UNIDADE_ORCAMENTARIA", referencedColumnName = "CD_UNIDADE_ORCAMENTARIA"),
			@JoinColumn( name = "CD_EXERCICIO", referencedColumnName = "CD_EXERCICIO")
			})
	private FIPLANUnidadeOrcamentaria unidadeOrcamentaria;

	@Column(name = "MES_RECURSO_FINANC", length = 9, nullable = false)
	@Type(type = DominioMes.NOME)
	private DominioMes mesRecursoFinanceiro;

	@Column(name = "VALR_PROGRAMADO", precision = 15, scale = 2, nullable = false)
	private BigDecimal valorProgramado;

	@Column(name = "FLAG_SITUACAO_TRAMITE", columnDefinition = "VARCHAR2(35) DEFAULT 'EM_ELABORACAO'")
	@Type(type = DominioSituacaoCadastroRF.NOME)
	private DominioSituacaoCadastroRF flagStatus = DominioSituacaoCadastroRF.EM_ELABORACAO;

	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)")
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao = DominioSituacaoRegistro.ATIVO;

	// Valor Total Complementar
	public BigDecimal getValorTotalComplementar() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (HistoricoValorCompra valorCompra : this.getHistoricoValorCompraList()) {
			valorTotal = valorTotal.add(valorCompra.getValorComplementar());
		}
		return valorTotal;
	}

	// Valor Total Previsto
	public BigDecimal getValorTotalPrevisto() {
		if (this.valorProgramado == null) {
			this.valorProgramado = BigDecimal.ZERO;
		}
		return this.valorProgramado.add(this.getValorTotalComplementar());
	}

	// valor disponivel para detalhar
	public BigDecimal getValorDisponivelDetalhar(Detalhamento detalhamentoEmAlteracao) {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Detalhamento detalhamento : this.getDetalhamentoList()) {
		    if(detalhamentoEmAlteracao == null || !detalhamentoEmAlteracao.equals(detalhamento)){		
			if(detalhamento.getFlagLimiteEmprestimo().equals(DominioSimNao.NAO)) {
			    valorTotal = valorTotal.add(detalhamento.getValorDetalhado());
			}
		    }
		}
		BigDecimal valorComplementar = getValorTotalComplementar();
		return (this.valorProgramado.add(valorComplementar)).subtract(valorTotal);
	}
	

		
	

	/*
	 * OneToMany
	 */

	@OneToMany(mappedBy = "recursoFinanceiro", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Detalhamento> Detalhamentos = new HashSet<Detalhamento>();
	@Transient
	private List<Detalhamento> DetalhamentoList = new ArrayList<Detalhamento>();

	@OneToMany(mappedBy = "recursoFinanceiro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<HistoricoValorCompra> historicoValorCompras = new HashSet<HistoricoValorCompra>();
	@Transient
	private List<HistoricoValorCompra> historicoValorCompraList = new ArrayList<HistoricoValorCompra>();

}
