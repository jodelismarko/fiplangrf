package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiroView;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import java.math.BigDecimal;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastroRF;

public class RecursoFinanceiroViewCriteria extends DynamicSearchCriteria<RecursoFinanceiroView> {

	@FilterFieldOption(label = "Exec Recurso Financeiro ", property = "execRecursoFinanceiro", type = FieldType.INTEGER)
	private Integer execRecursoFinanceiro;

	@FilterFieldOption(label = "Codigo ", property = "codigo", type = FieldType.INTEGER)
	private Integer codigo;

	@FilterFieldOption(label = "Mes Recurso Financeiro ", property = "mesRecursoFinanceiro", type = FieldType.ENUM)
	private DominioMes mesRecursoFinanceiro;

	@FilterFieldOption(label = "Valor Programado ", property = "valorProgramado", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorProgramado;

	@FilterFieldOption(label = "Valor Complementar ", property = "valorComplementar", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorComplementar;

	@FilterFieldOption(label = "Valor Total ", property = "valorTotal", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorTotal;

	@FilterFieldOption(label = "Id Usuario ", property = "idUsuario", type = FieldType.INTEGER)
	private Integer idUsuario;

	@FilterFieldOption(label = "Flag Status ", property = "flagStatus", type = FieldType.ENUM)
	private DominioSituacaoCadastroRF flagStatus;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Valor Total Repassado ", property = "valorTotalRepassado", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorTotalRepassado;

	public RecursoFinanceiroViewCriteria() {
		super(RecursoFinanceiroView.class);
	}

	public Integer getExecRecursoFinanceiro() {
		return execRecursoFinanceiro;
	}

	public void setExecRecursoFinanceiro(Integer execRecursoFinanceiro) {
		this.execRecursoFinanceiro = execRecursoFinanceiro;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public DominioMes getMesRecursoFinanceiro() {
		return mesRecursoFinanceiro;
	}

	public void setMesRecursoFinanceiro(DominioMes mesRecursoFinanceiro) {
		this.mesRecursoFinanceiro = mesRecursoFinanceiro;
	}

	public BigDecimal getValorProgramado() {
		return valorProgramado;
	}

	public void setValorProgramado(BigDecimal valorProgramado) {
		this.valorProgramado = valorProgramado;
	}

	public BigDecimal getValorComplementar() {
		return valorComplementar;
	}

	public void setValorComplementar(BigDecimal valorComplementar) {
		this.valorComplementar = valorComplementar;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public DominioSituacaoCadastroRF getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(DominioSituacaoCadastroRF flagStatus) {
		this.flagStatus = flagStatus;
	}

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	public BigDecimal getValorTotalRepassado() {
		return valorTotalRepassado;
	}

	public void setValorTotalRepassado(BigDecimal valorTotalRepassado) {
		this.valorTotalRepassado = valorTotalRepassado;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("execRecursoFinanceiro", execRecursoFinanceiro)
			.append("codigo", codigo)
			.append("mesRecursoFinanceiro", mesRecursoFinanceiro)
			.append("valorProgramado", valorProgramado)
			.append("valorComplementar", valorComplementar)
			.append("valorTotal", valorTotal)
			.append("idUsuario", idUsuario)
			.append("flagStatus", flagStatus)
			.append("flagSituacao", flagSituacao)
			.append("valorTotalRepassado", valorTotalRepassado)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
