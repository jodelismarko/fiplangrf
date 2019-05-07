package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoDespesa;

public class DetalhamentoDespesaCriteria extends DynamicSearchCriteria<DetalhamentoDespesa> {

	@FilterFieldOption(label = "Código", property = "codigoDetDespesa", type = FieldType.INTEGER, width="120")
	private Integer codigoDetDespesa;

	@FilterFieldOption(label = "Descrição", property = "descricaoDetDespesa", type = FieldType.TEXT)
	private String descricaoDetDespesa;

	public DetalhamentoDespesaCriteria() {
		super(DetalhamentoDespesa.class);
	}

	public Integer getCodigoDetDespesa() {
		return codigoDetDespesa;
	}

	public void setCodigoDetDespesa(Integer codigoDetDespesa) {
		this.codigoDetDespesa = codigoDetDespesa;
	}

	public String getDescricaoDetDespesa() {
		return descricaoDetDespesa;
	}

	public void setDescricaoDetDespesa(String descricaoDetDespesa) {
		this.descricaoDetDespesa = descricaoDetDespesa;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("codigoDetDespesa", codigoDetDespesa)
			.append("descricaoDetDespesa", descricaoDetDespesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
