package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import lombok.Getter;
import lombok.Setter;

public class TipoOcorrenciaCriteria extends DynamicSearchCriteria<TipoOcorrencia> {

	@Getter @Setter
	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, sort=true)
	private String descricao;

	@Getter @Setter
	@FilterFieldOption(label = "Situação", property = "situacao", type = FieldType.ENUM, labelProperty = "situacao.desc", width = "90")
	private DominioSituacao situacao;

	public TipoOcorrenciaCriteria() {
		super(TipoOcorrencia.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("descricao", descricao)
			.append("situacao", situacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
