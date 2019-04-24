package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;

public class DetalhamentoProvisaoDespesaCriteria extends DynamicSearchCriteria<DetalhamentoProvisaoDespesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoDetalheProvisao", type = FieldType.TEXT)
	private String descricaoDetalheProvisao;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Despesa ", property = "despesa", type = FieldType.LIST)
	private Despesa despesa;

	public DetalhamentoProvisaoDespesaCriteria() {
		super(DetalhamentoProvisaoDespesa.class);
	}

	public String getDescricaoDetalheProvisao() {
		return descricaoDetalheProvisao;
	}

	public void setDescricaoDetalheProvisao(String descricaoDetalheProvisao) {
		this.descricaoDetalheProvisao = descricaoDetalheProvisao;
	}

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricaoDetalheProvisao", descricaoDetalheProvisao)
			.append("flagSituacao", flagSituacao)
			.append("despesa", despesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
