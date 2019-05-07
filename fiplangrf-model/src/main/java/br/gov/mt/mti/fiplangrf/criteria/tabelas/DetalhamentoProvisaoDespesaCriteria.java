package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoProvisaoDespesa;

public class DetalhamentoProvisaoDespesaCriteria extends DynamicSearchCriteria<DetalhamentoProvisaoDespesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoDetalheProvisao", type = FieldType.TEXT)
	private String descricaoDetalheProvisao;

	@FilterFieldOption(label = "Despesa", property = "despesa.descricaoDespesa", type = FieldType.TEXT, fieldVisibility = FieldVisibility.FILTER)
	private  String despesaFiltro;
	
	@FilterFieldOption(label = "Despesa ", property = "despesa.descricaoDespesa", type = FieldType.LIST, fieldVisibility = FieldVisibility.GRID)
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
			.append("despesa", despesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
