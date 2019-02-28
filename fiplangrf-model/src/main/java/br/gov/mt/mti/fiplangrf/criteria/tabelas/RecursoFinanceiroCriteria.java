package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import java.math.BigDecimal;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastroRF;

public class RecursoFinanceiroCriteria extends DynamicSearchCriteria<RecursoFinanceiro> {

	@FilterFieldOption(label = "Exec Recurso Financeiro ", property = "execRecursoFinanceiro", type = FieldType.INTEGER)
	private Integer execRecursoFinanceiro;

	@FilterFieldOption(label = "Unidade Orcamentaria ", property = "unidadeOrcamentaria", type = FieldType.TEXT)
	private FIPLANUnidadeOrcamentaria unidadeOrcamentaria;

	@FilterFieldOption(label = "Mes Recurso Financeiro ", property = "mesRecursoFinanceiro", type = FieldType.ENUM)
	private DominioMes mesRecursoFinanceiro;

	@FilterFieldOption(label = "Valor Programado ", property = "valorProgramado", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorProgramado;

	@FilterFieldOption(label = "Flag Status ", property = "flagStatus", type = FieldType.ENUM)
	private DominioSituacaoCadastroRF flagStatus;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	public RecursoFinanceiroCriteria() {
		super(RecursoFinanceiro.class);
	}

	public Integer getExecRecursoFinanceiro() {
		return execRecursoFinanceiro;
	}

	public void setExecRecursoFinanceiro(Integer execRecursoFinanceiro) {
		this.execRecursoFinanceiro = execRecursoFinanceiro;
	}

	public FIPLANUnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(FIPLANUnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("execRecursoFinanceiro", execRecursoFinanceiro)
			.append("unidadeOrcamentaria", unidadeOrcamentaria)
			.append("mesRecursoFinanceiro", mesRecursoFinanceiro)
			.append("valorProgramado", valorProgramado)
			.append("flagStatus", flagStatus)
			.append("flagSituacao", flagSituacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
