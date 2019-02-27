package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNaturezaJuridica;
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NaturezaJuridicaCriteria extends DynamicSearchCriteria<NaturezaJuridica> {

	@FilterFieldOption(label = "Número Natureza Juridica", property = "numrNaturezaJuridica", labelProperty = "numrNaturezaJuridicaFormatado", type = FieldType.LONG, width="40")
	private Long numrNaturezaJuridica;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, width="180")
	private String descricao;

	@FilterFieldOption(label = "Tipo Natureza Juridica", property = "tipoNaturezaJuridica", labelProperty = "tipoNaturezaJuridica.desc", type = FieldType.ENUM, width = "70")
	private DominioTipoNaturezaJuridica tipoNaturezaJuridica;

	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, width="50")
	private DominioSituacao situacao;

	public NaturezaJuridicaCriteria() {
		super(NaturezaJuridica.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("numrNaturezaJuridica", numrNaturezaJuridica)
			.append("descricao", descricao)
			.append("tipoNaturezaJuridica", tipoNaturezaJuridica)
			.append("situacao", situacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
