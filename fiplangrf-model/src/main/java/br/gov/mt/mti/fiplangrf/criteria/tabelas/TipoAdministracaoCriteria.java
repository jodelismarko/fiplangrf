package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TipoAdministracaoCriteria extends DynamicSearchCriteria<TipoAdministracao> {

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, width="180")
	private String descricao;

	@FilterFieldOption(label = "Tipo Poder", property = "tipoPoder", type = FieldType.ENUM, labelProperty = "tipoPoder.desc", width="50")
	private DominioTipoPoder tipoPoder;

	@FilterFieldOption(label = "Situação", property = "situacao", type = FieldType.ENUM, labelProperty = "situacao.desc", width="50")
	private DominioSituacao situacao;

	public TipoAdministracaoCriteria() {
		super(TipoAdministracao.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("descricao", descricao).append("tipoPoder", tipoPoder)
				.append("situacao", situacao).toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
