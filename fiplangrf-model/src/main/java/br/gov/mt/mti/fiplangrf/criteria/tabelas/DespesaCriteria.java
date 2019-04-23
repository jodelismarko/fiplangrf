package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Despesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioIndicativoProvisao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;

public class DespesaCriteria extends DynamicSearchCriteria<Despesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoDespesa", type = FieldType.TEXT)
	private String descricaoDespesa;

	@FilterFieldOption(label = "Flag Indicativo Provisao ", property = "flagIndicativoProvisao", type = FieldType.ENUM)
	private DominioIndicativoProvisao flagIndicativoProvisao;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

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

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricaoDespesa", descricaoDespesa)
			.append("flagIndicativoProvisao", flagIndicativoProvisao)
			.append("flagSituacao", flagSituacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
