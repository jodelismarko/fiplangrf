package br.gov.mt.mti.fiplangrf.criteria.auditoria;


import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.auditoria.CustomRevisionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomRevisionEntityCriteria extends DynamicSearchCriteria<CustomRevisionEntity> {

	@FilterFieldOption(label = "Nome Usuario ", property = "nomeUsuario", type = FieldType.TEXT )
	private String nomeUsuario;

	@FilterFieldOption(label = "Numero CPF Usuario ", property = "numeroCPFUsuario", type = FieldType.TEXT )
	private String numeroCPFUsuario;

	@FilterFieldOption(label = "Endereco IP ", property = "enderecoIP", type = FieldType.TEXT )
	private String enderecoIP;

	@FilterFieldOption(label = "Codigo Usuario Cepro Security ", property = "codigoUsuarioCeproSecurity", type = FieldType.LONG )
	private Long codigoUsuarioCeproSecurity;
	
    public CustomRevisionEntityCriteria() {
    	super(CustomRevisionEntity.class);
    }

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("nomeUsuario", nomeUsuario)
			.append("numeroCPFUsuario", numeroCPFUsuario)
			.append("enderecoIP", enderecoIP)
			.append("codigoUsuarioCeproSecurity", codigoUsuarioCeproSecurity)
			.toString();
	}
}
