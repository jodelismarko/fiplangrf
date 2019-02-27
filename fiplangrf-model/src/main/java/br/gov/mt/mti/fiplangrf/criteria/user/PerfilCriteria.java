package br.gov.mt.mti.fiplangrf.criteria.user;

import java.util.Collection;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.Expression;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PerfilCriteria extends DynamicSearchCriteria<Perfil> {

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT, width = "180" )
	private String nome;

	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, dataSource = DominioSituacao.class, width = "60")
	private DominioSituacao situacao;

    public PerfilCriteria() {
    	super(Perfil.class);
    }

	/**
	 * Método que irá excluir da consulta os registros cujos Ids sejam informados como
	 * parâmetro.
	 * @param perfis
	 */
	public void setExceptPerfilId(Collection<Perfil> perfis){		
		for(Perfil perfil : perfis) {
			getModel().configMultipleValueParameter("id", perfil.getId().toString(), Expression.DIFFERENT, true);
		}		
	}
}
