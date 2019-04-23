package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;

public class FonteRecursoCriteria extends DynamicSearchCriteria<FonteRecurso> {

	@FilterFieldOption(label = "Codg Fonte Recurso ", property = "codgFonteRecurso", type = FieldType.INTEGER)
	private Integer codgFonteRecurso;

	@FilterFieldOption(label = "Descricao Fonte Recurso ", property = "descricaoFonteRecurso", type = FieldType.TEXT)
	private String descricaoFonteRecurso;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

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

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("codgFonteRecurso", codgFonteRecurso)
			.append("descricaoFonteRecurso", descricaoFonteRecurso)
			.append("flagSituacao", flagSituacao)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
