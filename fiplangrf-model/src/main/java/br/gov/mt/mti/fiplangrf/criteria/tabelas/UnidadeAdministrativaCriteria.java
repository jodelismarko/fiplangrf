package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastralCnpj;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;
import br.gov.mt.mti.fiplangrf.model.tabelas.NaturezaJuridica;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeAdministrativa;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnidadeAdministrativaCriteria extends DynamicSearchCriteria<UnidadeAdministrativa> {

	@FilterFieldOption(label = "CNPJ", property = "numrCNPJ", labelProperty = "numrCNPJFormatado", type = FieldType.LONG, width = "175")
	private String numrCNPJ;

	@FilterFieldOption(label = "Sigla", property = "sigla", type = FieldType.TEXT, width = "110")
	private String sigla;

	@FilterFieldOption(label = "Nome Empresarial", property = "nomeEmpresarial", type = FieldType.TEXT, width = "260")
	private String nomeEmpresarial;

	@FilterFieldOption(label = "Unidade Orçamentária", property = "unidadeOrcamentaria", labelProperty="unidadeOrcamentariaFormatado", type = FieldType.TEXT, width="210")
	private String descricaoUnidadeOrcamentaria;
	
	@FilterFieldOption(label = "Tipo Poder", property = "tipoPoder", labelProperty = "tipoPoder.desc", type = FieldType.ENUM, width = "190")
	private DominioTipoPoder tipoPoder;

	@FilterFieldOption(label = "Tipo Administração", property = "tipoAdministracao.descricao", type = FieldType.TEXT, width = "200")
	private TipoAdministracao tipoAdministracao;

	@FilterFieldOption(label = "Natureza Jurídica ", property = "naturezaJuridica", labelProperty = "naturezaJuridica.descricaoFormatado",type = FieldType.TEXT, width = "210")
	private NaturezaJuridica naturezaJuridica;
	
	@FilterFieldOption(label = "Situação CNPJ", property = "situacaoCnpj", labelProperty = "situacaoCnpj.desc", type = FieldType.ENUM, width = "150")
	private DominioSituacaoCadastralCnpj situacaoCnpj;
	
	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, width = "150")
	private DominioSituacao situacao;
	
	public UnidadeAdministrativaCriteria() {
		super(UnidadeAdministrativa.class);
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
