package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;

public class FonteRecursoCriteria extends DynamicSearchCriteria<FonteRecurso> {

	@FilterFieldOption(label = "Código", property = "codgFonteRecurso", type = FieldType.INTEGER, width="120")
	private Integer codgFonteRecurso;

	@FilterFieldOption(label = "Descrição da Fonte Recurso ", property = "descricaoFonteRecurso", type = FieldType.TEXT)
	private String descricaoFonteRecurso;

	public FonteRecursoCriteria() {
		super(FonteRecurso.class);
	}

	public Integer getCodgFonteRecurso() {
		return codgFonteRecurso;
	}

	public void setCodgFonteRecurso(Integer codgFonteRecurso) {
		this.codgFonteRecurso = codgFonteRecurso;
	}

	public String getDescricaoFonteRecurso() {
		return descricaoFonteRecurso;
	}

	public void setDescricaoFonteRecurso(String descricaoFonteRecurso) {
		this.descricaoFonteRecurso = descricaoFonteRecurso;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("codgFonteRecurso", codgFonteRecurso)
			.append("descricaoFonteRecurso", descricaoFonteRecurso)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
