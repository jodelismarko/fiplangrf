package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioIndicativoProvisao;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;

public class DespesaCriteria extends DynamicSearchCriteria<Despesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoDespesa", type = FieldType.TEXT)
	private String descricaoDespesa;

	@FilterFieldOption(label = "Indicativo Provisao ", property = "flagIndicativoProvisao", type = FieldType.ENUM, width="240")
	private DominioIndicativoProvisao flagIndicativoProvisao;

	public DespesaCriteria() {
		super(Despesa.class);
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public DominioIndicativoProvisao getFlagIndicativoProvisao() {
		return flagIndicativoProvisao;
	}

	public void setFlagIndicativoProvisao(DominioIndicativoProvisao flagIndicativoProvisao) {
		this.flagIndicativoProvisao = flagIndicativoProvisao;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricaoDespesa", descricaoDespesa)
			.append("flagIndicativoProvisao", flagIndicativoProvisao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
