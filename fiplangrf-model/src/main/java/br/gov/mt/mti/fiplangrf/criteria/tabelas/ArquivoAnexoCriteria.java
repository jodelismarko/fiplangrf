package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;

public class ArquivoAnexoCriteria extends DynamicSearchCriteria<ArquivoAnexo> {

	public ArquivoAnexoCriteria() {
		super(ArquivoAnexo.class);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
