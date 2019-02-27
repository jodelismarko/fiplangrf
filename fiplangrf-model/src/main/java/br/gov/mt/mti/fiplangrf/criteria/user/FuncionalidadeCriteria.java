package br.gov.mt.mti.fiplangrf.criteria.user;

import java.util.Collection;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.Expression;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FuncionalidadeCriteria extends DynamicSearchCriteria<Funcionalidade> {

	@FilterFieldOption(label = "Id", property = "id", type = FieldType.LONG, width = "60", fieldVisibility= {FieldVisibility.GRID})
	private Long id;
	
	@FilterFieldOption(label = "Nome", property = "nome", type = FieldType.TEXT)
	private String nome;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT )
	private String descricao;
	
	@FilterFieldOption(label = "Permissão", property = "permissao.id", labelProperty="permissao.nome", 
			join="permissoes", alias="permissao", type = FieldType.LUPA, 
			fieldVisibility=FieldVisibility.FILTER)
	private Permissao permissao;
		
	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, dataSource = DominioSituacao.class)
	private DominioSituacao situacao;
	
	
    public FuncionalidadeCriteria() {
    	super(Funcionalidade.class);
    }

	/**
	 * Método que irá excluir da consulta os registros cujos Ids sejam informados como
	 * parâmetro.
	 * @param funcionalidades
	 */
	public void setExceptFuncionalidadeId(Collection<Funcionalidade> funcionalidades){		
		for(Funcionalidade funcionalidade : funcionalidades) {
			getModel().configMultipleValueParameter("id", funcionalidade.getId().toString(), Expression.DIFFERENT, true);
		}		
	}
	
	@Override
	public boolean isUseCriteria() {	
		return true;
	}
}

