@TypeDefs(
			{
				@TypeDef(
			    		name = DominioSimNao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSimNao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSimNao.METHOD)}),
				@TypeDef(
						name = DominioNaturezaDespesa.NOME, 
						typeClass = MTIFWGenericEnumUserType.class, 
						parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioNaturezaDespesa.NOME),
								@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioNaturezaDespesa.METHOD)}),
				@TypeDef(
						name = DominioTetoFinanceiroFiplan.NOME, 
						typeClass = MTIFWGenericEnumUserType.class, 
						parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTetoFinanceiroFiplan.NOME),
								@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTetoFinanceiroFiplan.METHOD)}),
				@TypeDef(
						name = DominioIndicativoProvisao.NOME, 
						typeClass = MTIFWGenericEnumUserType.class, 
						parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioIndicativoProvisao.NOME),
								@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioIndicativoProvisao.METHOD)}),
				
			    @TypeDef(
			    		name = DominioSituacao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacao.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioSituacaoRegistro.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoRegistro.NOME),
			    				@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoRegistro.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoPermissao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoPermissao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoPermissao.METHOD)}),
			    
				@TypeDef(
					    name = DominioMensagem.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioMensagem.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioMensagem.METHOD)}),
									  
				 @TypeDef(
					    name = DominioMes.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioMes.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioMes.METHOD)}),
			   
			   }
		)
package br.gov.mt.mti.fiplangrf.model;


import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.gov.mt.mti.fiplangrf.dominio.DominioIndicativoProvisao;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.dominio.DominioNaturezaDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTetoFinanceiroFiplan;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.dominio.MTIFWGenericEnumUserType;