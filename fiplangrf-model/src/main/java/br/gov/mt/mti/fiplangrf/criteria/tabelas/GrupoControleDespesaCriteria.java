package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoControleDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioTetoFinanceiroFiplan;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import java.math.BigInteger;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;

public class GrupoControleDespesaCriteria extends DynamicSearchCriteria<GrupoControleDespesa> {

	@FilterFieldOption(label = "Codigo Grupo Controle Despesa ", property = "codigoGrupoControleDespesa", type = FieldType.TEXT)
	private BigInteger codigoGrupoControleDespesa;

	@FilterFieldOption(label = "Descrição", property = "descricaoGrupoControleDespesa", type = FieldType.TEXT)
	private String descricaoGrupoControleDespesa;

	@FilterFieldOption(label = "Flag Teto Financeiro Fiplan ", property = "flagTetoFinanceiroFiplan", type = FieldType.ENUM)
	private DominioTetoFinanceiroFiplan flagTetoFinanceiroFiplan;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Despesa ", property = "despesa", type = FieldType.LIST)
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
			.append("codigoGrupoControleDespesa", codigoGrupoControleDespesa)
			.append("descricaoGrupoControleDespesa", descricaoGrupoControleDespesa)
			.append("flagTetoFinanceiroFiplan", flagTetoFinanceiroFiplan)
			.append("flagSituacao", flagSituacao)
			.append("despesa", despesa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
