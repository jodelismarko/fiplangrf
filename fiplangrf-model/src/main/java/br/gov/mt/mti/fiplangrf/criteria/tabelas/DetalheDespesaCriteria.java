package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;

public class DetalheDespesaCriteria extends DynamicSearchCriteria<DetalhamentoDespesa> {

	@FilterFieldOption(label = "Codigo Det Despesa ", property = "codigoDetDespesa", type = FieldType.INTEGER)
	private Integer codigoDetDespesa;

	@FilterFieldOption(label = "Descrição", property = "descricaoDetDespesa", type = FieldType.TEXT)
	private String descricaoDetDespesa;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	public DetalheDespesaCriteria() {
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

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("codigoDetDespesa", codigoDetDespesa)
			.append("descricaoDetDespesa", descricaoDetDespesa)
			.append("flagSituacao", flagSituacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
