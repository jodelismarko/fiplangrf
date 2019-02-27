package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GrupoPendenciaCriteria extends DynamicSearchCriteria<GrupoPendencia> {

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT)
	private String descricao;

	@FilterFieldOption(label = "Tipo Ocorrência ", property = "tipoOcorrencia.id", labelProperty = "tipoOcorrencia.descricao", type = FieldType.LIST, alias = "tipoOcorrencia", join = "tipoOcorrencia", beanDataSource = "#{combosListaHelperBean.listarTipoOcorrencia()}")
	private TipoOcorrencia tipoOcorrencia;

	@FilterFieldOption(label = "Situação", property = "situacao", type = FieldType.ENUM, labelProperty = "situacao.desc", width = "80")
	private DominioSituacao situacao;

	public GrupoPendenciaCriteria() {
		super(GrupoPendencia.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("descricao", descricao).append("tipoOcorrencia", tipoOcorrencia)
				.append("situacao", situacao).toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
