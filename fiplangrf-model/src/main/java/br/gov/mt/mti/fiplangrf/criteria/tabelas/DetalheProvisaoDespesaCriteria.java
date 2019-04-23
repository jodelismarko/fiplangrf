package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;

public class DetalheProvisaoDespesaCriteria extends DynamicSearchCriteria<DetalheProvisaoDespesa> {

	@FilterFieldOption(label = "Descrição", property = "descricaoDetalheProvisao", type = FieldType.TEXT)
	private String descricaoDetalheProvisao;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	public DetalheProvisaoDespesaCriteria() {
		super(DetalheProvisaoDespesa.class);
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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricaoDetalheProvisao", descricaoDetalheProvisao)
			.append("flagSituacao", flagSituacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
