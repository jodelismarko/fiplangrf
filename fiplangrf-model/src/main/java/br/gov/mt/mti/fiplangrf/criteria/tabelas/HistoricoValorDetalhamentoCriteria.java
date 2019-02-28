package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.HistoricoValorDetalhamento;
import java.math.BigDecimal;
import java.util.Date;
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacaoNLA;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public class HistoricoValorDetalhamentoCriteria extends DynamicSearchCriteria<HistoricoValorDetalhamento> {

	@FilterFieldOption(label = "Data Operacao ", property = "dataOperacao", type = FieldType.TEXT)
	private Date dataOperacao;

	@FilterFieldOption(label = "Valor Detalhado ", property = "valorDetalhado", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorDetalhado;

	@FilterFieldOption(label = "Valor NLA ", property = "valorNLA", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorNLA;

	@FilterFieldOption(label = "Flag Operacao NLA ", property = "flagOperacaoNLA", type = FieldType.ENUM)
	private DominioTipoOperacaoNLA flagOperacaoNLA;

	@FilterFieldOption(label = "Usuario ", property = "usuario", type = FieldType.TEXT)
	private Usuario usuario;

	@FilterFieldOption(label = "Detalhamento ", property = "detalhamento", type = FieldType.LIST)
	private Detalhamento detalhamento;

	public HistoricoValorDetalhamentoCriteria() {
		super(HistoricoValorDetalhamento.class);
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public BigDecimal getValorDetalhado() {
		return valorDetalhado;
	}

	public void setValorDetalhado(BigDecimal valorDetalhado) {
		this.valorDetalhado = valorDetalhado;
	}

	public BigDecimal getValorNLA() {
		return valorNLA;
	}

	public void setValorNLA(BigDecimal valorNLA) {
		this.valorNLA = valorNLA;
	}

	public DominioTipoOperacaoNLA getFlagOperacaoNLA() {
		return flagOperacaoNLA;
	}

	public void setFlagOperacaoNLA(DominioTipoOperacaoNLA flagOperacaoNLA) {
		this.flagOperacaoNLA = flagOperacaoNLA;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Detalhamento getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(Detalhamento detalhamento) {
		this.detalhamento = detalhamento;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("dataOperacao", dataOperacao)
			.append("valorDetalhado", valorDetalhado)
			.append("valorNLA", valorNLA)
			.append("flagOperacaoNLA", flagOperacaoNLA)
			.append("usuario", usuario)
			.append("detalhamento", detalhamento)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
