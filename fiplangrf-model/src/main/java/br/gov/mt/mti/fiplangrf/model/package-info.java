@TypeDefs(
			{
				@TypeDef(
			    		name = DominioSimNao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSimNao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSimNao.METHOD)}),
				
			    @TypeDef(
			    		name = DominioSituacao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacao.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoPermissao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoPermissao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoPermissao.METHOD)}),
			   
			   }
		)
package br.gov.mt.mti.fiplangrf.model;


import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.dominio.MTIFWGenericEnumUserType;