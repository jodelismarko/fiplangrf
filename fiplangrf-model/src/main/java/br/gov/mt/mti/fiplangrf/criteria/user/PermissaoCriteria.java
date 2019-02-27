package br.gov.mt.mti.fiplangrf.criteria.user;

import java.util.Collection;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.Expression;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PermissaoCriteria extends DynamicSearchCriteria<Permissao> {


	@FilterFieldOption(label = "Id", property = "id", type = FieldType.LONG, width = "60", fieldVisibility= {FieldVisibility.GRID})
	private Long id;

	@FilterFieldOption(label = "Nome", property = "nome", type = FieldType.TEXT)
	private String nome;

	@FilterFieldOption(label = "Ação", property = "action", type = FieldType.TEXT )
	private String action;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT )
	private String descricao;

	@FilterFieldOption(label = "Tipo de permissão", property = "tipoPermissao", labelProperty = "tipoPermissao.desc", type = FieldType.ENUM, dataSource = DominioTipoPermissao.class)
	private DominioTipoPermissao tipoPermissao;


    public PermissaoCriteria() {
    	super(Permissao.class);
    }

	public void setExceptPermissaoId(Collection<Permissao> permissoes){		
		for(Permissao permissao : permissoes) {
			getModel().configMultipleValueParameter("id", permissao.getId().toString(), Expression.DIFFERENT, true);
		}		
	}
    
}
