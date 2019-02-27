package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAE;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CNAECriteria extends DynamicSearchCriteria<CNAE> {

	@FilterFieldOption(label = "Número CNAE", property = "numeroCNAE", labelProperty = "numeroCNAEFormatado", type = FieldType.LONG, width = "40")
	private Long numeroCNAE;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, width = "200")
	private String descricao;

	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, width = "60")
	private DominioSituacao situacao;

	public CNAECriteria() {
		super(CNAE.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("numeroCNAE", numeroCNAE).append("descricao", descricao)
				.append("situacao", situacao).toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}
}
