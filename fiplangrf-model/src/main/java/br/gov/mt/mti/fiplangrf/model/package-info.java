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
			    
			    @TypeDef(
			    		name = DominioGrupoControleTF.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioGrupoControleTF.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioGrupoControleTF.METHOD)}),

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
									  
	  
				@TypeDef(
					    name = DominioSituacaoCadastroRF.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoCadastroRF.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoCadastroRF.METHOD)}),
									  
									  
				@TypeDef(
					    name = DominioSituacaoDoc.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoDoc.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoDoc.METHOD)}),
									  
				@TypeDef(
					    name = DominioSituacaoRegistro.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoRegistro.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoRegistro.METHOD)}),
									  
				@TypeDef(
					    name = DominioTipoDocumentoRF.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoDocumentoRF.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoDocumentoRF.METHOD)}),
									  
				@TypeDef(
					    name = DominioTipoNLA.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoNLA.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoNLA.METHOD)}),
									  
				@TypeDef(
					    name = DominioTipoOperacaoNLA.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoOperacaoNLA.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoOperacaoNLA.METHOD)}),
								
			   
			   }
		)
package br.gov.mt.mti.fiplangrf.model;


import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.gov.mt.mti.fiplangrf.dominio.DominioGrupoControleTF;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastroRF;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoDocumentoRF;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNLA;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacaoNLA;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.dominio.MTIFWGenericEnumUserType;