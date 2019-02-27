package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TipoPendenciaCriteria extends DynamicSearchCriteria<TipoPendencia> {

	@FilterFieldOption(label = "Sigla", property = "sigla", type = FieldType.TEXT, width = "100")
	private String sigla;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, width = "200")
	private String descricao;

	@FilterFieldOption(label = "Tipo Ocorrência ", property = "tipoOcorrencia.id", labelProperty = "tipoOcorrencia.descricao", type = FieldType.LIST, alias = "tipoOcorrencia", join = "tipoOcorrencia", beanDataSource = "#{combosListaHelperBean.listarTipoOcorrencia()}", width = "100")
	private TipoOcorrencia tipoOcorrencia;

	@FilterFieldOption(label = "Situação", property = "situacao", type = FieldType.ENUM, labelProperty = "situacao.desc", width = "40")
	private DominioSituacao situacao;

	public TipoPendenciaCriteria() {
		super(TipoPendencia.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sigla", sigla).append("descricao", descricao)
				.append("tipoOcorrencia", tipoOcorrencia).append("situacao", situacao).toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
