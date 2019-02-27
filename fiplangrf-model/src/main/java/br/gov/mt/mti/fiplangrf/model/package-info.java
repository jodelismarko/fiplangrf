@TypeDefs(
			{
				@TypeDef(
			    		name = DominioSimNao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSimNao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSimNao.METHOD)}),
				
			    @TypeDef(
			    		name = DominioSimNaoFIPLAN.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSimNaoFIPLAN.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSimNaoFIPLAN.METHOD)}),
		
			    @TypeDef(
			    		name = DominioSituacao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacao.METHOD)}),
			    @TypeDef(
			    		name = DominioSituacaoCadastralCnpj.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoCadastralCnpj.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoCadastralCnpj.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioSituacaoPendencia.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoPendencia.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoPendencia.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoLegislacao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoLegislacao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoLegislacao.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoNaturezaJuridica.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoNaturezaJuridica.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoNaturezaJuridica.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoPermissao.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoPermissao.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoPermissao.METHOD)}),
			   
			    @TypeDef(
			    		name = DominioTipoPoder.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoPoder.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoPoder.METHOD)}),
			    
			    @TypeDef(
			    		name = DominioTipoAtividade.NOME, 
			    		typeClass = MTIFWGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoAtividade.NOME),
			    				      @Parameter(name = MTIFWGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoAtividade.METHOD)})
			}
		)
package br.gov.mt.mti.fiplangrf.model;


import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNaoFIPLAN;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastralCnpj;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoAtividade;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoLegislacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNaturezaJuridica;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;
import br.gov.mt.mti.fiplangrf.dominio.MTIFWGenericEnumUserType;