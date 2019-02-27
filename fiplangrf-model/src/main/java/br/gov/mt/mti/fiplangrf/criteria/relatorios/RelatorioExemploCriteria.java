package br.gov.mt.mti.fiplangrf.criteria.relatorios;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public class RelatorioExemploCriteria extends DynamicSearchCriteria<Usuario> {

	public RelatorioExemploCriteria() {
		super(Usuario.class);
	}

	@FilterFieldOption(label = "Nome", labelProperty = "Nome", property = "nome", type = FieldType.TEXT, required=true, fieldVisibility= {FieldVisibility.FILTER} )
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("nome", nome).toString();
	}

}
