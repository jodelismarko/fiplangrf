package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorCompra;
import java.math.BigDecimal;
import java.util.Date;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public class HistoricoValorCompraCriteria extends DynamicSearchCriteria<HistoricoValorCompra> {

	@FilterFieldOption(label = "Data Valor Complementar ", property = "dataValorComplementar", type = FieldType.TEXT)
	private Date dataValorComplementar;

	@FilterFieldOption(label = "Justificativa ", property = "justificativa", type = FieldType.TEXT)
	private String justificativa;

	@FilterFieldOption(label = "Valor Complementar ", property = "valorComplementar", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorComplementar;

	@FilterFieldOption(label = "Usuario ", property = "usuario", type = FieldType.TEXT)
	private Usuario usuario;

	@FilterFieldOption(label = "Recurso Financeiro ", property = "recursoFinanceiro", type = FieldType.LIST)
	private RecursoFinanceiro recursoFinanceiro;

	public HistoricoValorCompraCriteria() {
		super(HistoricoValorCompra.class);
	}

	public Date getDataValorComplementar() {
		return dataValorComplementar;
	}

	public void setDataValorComplementar(Date dataValorComplementar) {
		this.dataValorComplementar = dataValorComplementar;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public BigDecimal getValorComplementar() {
		return valorComplementar;
	}

	public void setValorComplementar(BigDecimal valorComplementar) {
		this.valorComplementar = valorComplementar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RecursoFinanceiro getRecursoFinanceiro() {
		return recursoFinanceiro;
	}

	public void setRecursoFinanceiro(RecursoFinanceiro recursoFinanceiro) {
		this.recursoFinanceiro = recursoFinanceiro;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("dataValorComplementar", dataValorComplementar)
			.append("justificativa", justificativa)
			.append("valorComplementar", valorComplementar)
			.append("usuario", usuario)
			.append("recursoFinanceiro", recursoFinanceiro)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
