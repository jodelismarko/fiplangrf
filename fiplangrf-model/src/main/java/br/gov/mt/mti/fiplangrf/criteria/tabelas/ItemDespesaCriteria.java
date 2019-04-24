package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.ItemDespesa;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioNaturezaDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;

public class ItemDespesaCriteria extends DynamicSearchCriteria<ItemDespesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoItemDespesa", type = FieldType.TEXT)
	private String descricaoItemDespesa;

	@FilterFieldOption(label = "Flag Natureza Despesa ", property = "flagNaturezaDespesa", type = FieldType.ENUM)
	private DominioNaturezaDespesa flagNaturezaDespesa;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Grupo Controle Despesa ", property = "grupoControleDespesa", type = FieldType.LIST)
	private GrupoControleDespesa grupoControleDespesa;

	public ItemDespesaCriteria() {
		super(ItemDespesa.class);
	}

	public String getDescricaoItemDespesa() {
		return descricaoItemDespesa;
	}

	public void setDescricaoItemDespesa(String descricaoItemDespesa) {
		this.descricaoItemDespesa = descricaoItemDespesa;
	}

	public DominioNaturezaDespesa getFlagNaturezaDespesa() {
		return flagNaturezaDespesa;
	}

	public void setFlagNaturezaDespesa(DominioNaturezaDespesa flagNaturezaDespesa) {
		this.flagNaturezaDespesa = flagNaturezaDespesa;
	}

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	public GrupoControleDespesa getGrupoControleDespesa() {
		return grupoControleDespesa;
	}

	public void setGrupoControleDespesa(GrupoControleDespesa grupoControleDespesa) {
		this.grupoControleDespesa = grupoControleDespesa;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricaoItemDespesa", descricaoItemDespesa)
			.append("flagNaturezaDespesa", flagNaturezaDespesa)
			.append("flagSituacao", flagSituacao)
			.append("grupoControleDespesa", grupoControleDespesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
