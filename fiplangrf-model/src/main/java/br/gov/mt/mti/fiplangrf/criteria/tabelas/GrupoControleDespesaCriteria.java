package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import java.math.BigInteger;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioTetoFinanceiroFiplan;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;

public class GrupoControleDespesaCriteria extends DynamicSearchCriteria<GrupoControleDespesa> {

	@FilterFieldOption(label = "Código", property = "codigoGrupoControleDespesa", type = FieldType.TEXT)
	private BigInteger codigoGrupoControleDespesa;

	@FilterFieldOption(label = "Descrição", property = "descricaoGrupoControleDespesa", type = FieldType.TEXT)
	private String descricaoGrupoControleDespesa;

	@FilterFieldOption(label = "Teto Financeiro Fiplan ", property = "flagTetoFinanceiroFiplan", type = FieldType.ENUM)
	private DominioTetoFinanceiroFiplan flagTetoFinanceiroFiplan;

	@FilterFieldOption(label = "Despesa", property = "despesa.descricaoDespesa", type = FieldType.TEXT, fieldVisibility = FieldVisibility.FILTER)
	private  String despesaFiltro;
	
	@FilterFieldOption(label = "Despesa ", property = "despesa.descricaoDespesa", type = FieldType.LIST, fieldVisibility = FieldVisibility.GRID)
	private Despesa despesa;
	
	public GrupoControleDespesaCriteria() {
		super(GrupoControleDespesa.class);
	}

	public BigInteger getCodigoGrupoControleDespesa() {
		return codigoGrupoControleDespesa;
	}

	public void setCodigoGrupoControleDespesa(BigInteger codigoGrupoControleDespesa) {
		this.codigoGrupoControleDespesa = codigoGrupoControleDespesa;
	}

	public String getDescricaoGrupoControleDespesa() {
		return descricaoGrupoControleDespesa;
	}

	public void setDescricaoGrupoControleDespesa(String descricaoGrupoControleDespesa) {
		this.descricaoGrupoControleDespesa = descricaoGrupoControleDespesa;
	}

	public DominioTetoFinanceiroFiplan getFlagTetoFinanceiroFiplan() {
		return flagTetoFinanceiroFiplan;
	}

	public void setFlagTetoFinanceiroFiplan(DominioTetoFinanceiroFiplan flagTetoFinanceiroFiplan) {
		this.flagTetoFinanceiroFiplan = flagTetoFinanceiroFiplan;
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
			.append("codigoGrupoControleDespesa", codigoGrupoControleDespesa)
			.append("descricaoGrupoControleDespesa", descricaoGrupoControleDespesa)
			.append("flagTetoFinanceiroFiplan", flagTetoFinanceiroFiplan)
			.append("despesa", despesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
