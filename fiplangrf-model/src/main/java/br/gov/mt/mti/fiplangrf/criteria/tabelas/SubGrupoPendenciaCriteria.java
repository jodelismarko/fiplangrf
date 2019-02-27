package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.SubGrupoPendencia;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubGrupoPendenciaCriteria extends DynamicSearchCriteria<SubGrupoPendencia> {

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT)
	private String descricao;

	@FilterFieldOption(label = "Grupo Pendência", property = "grupoPendencia.id", labelProperty = "grupoPendencia.descricao", type = FieldType.LIST, alias = "grupoPendencia", join = "grupoPendencia", beanDataSource = "#{combosListaHelperBean.listarGrupoPendencia()}")
	private GrupoPendencia grupoPendencia;

	@FilterFieldOption(label = "Situação", property = "situacao", type = FieldType.ENUM, labelProperty = "situacao.desc", width = "90")
	private DominioSituacao situacao;

	public SubGrupoPendenciaCriteria() {
		super(SubGrupoPendencia.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("descricao", descricao).append("grupoPendencia", grupoPendencia)
				.append("situacao", situacao).toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
