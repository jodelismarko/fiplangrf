package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import java.util.Date;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldFormatterOptions;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastralCnpj;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.Ocorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import br.gov.mt.mti.fiplangrf.util.formatter.DateFormatter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OcorrenciaCriteria extends DynamicSearchCriteria<Ocorrencia> {

	@FieldFormatterOptions(pattern="dd/MM/yyyy")
	@FilterFieldOption(label = "Data Inscrição", property = "dataInscricao", formatter= DateFormatter.class, type = FieldType.DATE, width="110")
	private Date dataInscricao;
	
	@FilterFieldOption(label = "CNPJ", property = "unidadeAdministrativa.numrCNPJ", labelProperty="unidadeAdministrativa.numrCNPJFormatado", type = FieldType.LONG, width="135")
	private String numCNPJ;
	
	@FilterFieldOption(label = "Nome Empresarial", property = "unidadeAdministrativa.nomeEmpresarial", labelProperty="unidadeAdministrativa.nomeEmpresarial", type = FieldType.TEXT, width="300")
	private String nomeEmpresarial;
	
	@FilterFieldOption(label = "Situação Cadastral CNPJ", property = "unidadeAdministrativa.situacaoCnpj", labelProperty="unidadeAdministrativa.situacaoCnpj.desc", type = FieldType.ENUM, width="175")
	private DominioSituacaoCadastralCnpj situacaoCNPJ;
	
	@FilterFieldOption(label = "Unidade Orçamentária", property = "unidadeOrcamentaria", labelProperty="unidadeOrcamentariaFormatado", type = FieldType.TEXT, width="170")
	private String descricaoUnidadeOrcamentaria;
	
	@FilterFieldOption(label = "Tipo Ocorrência", property = "tipoOcorrencia.descricao", labelProperty = "tipoOcorrencia.descricao", type = FieldType.TEXT, width="150")
	private TipoOcorrencia tipoOcorrencia;

	@FilterFieldOption(label = "Tipo Pendência", property = "tipoPendencia.sigla", labelProperty = "tipoPendencia.sigla", type = FieldType.TEXT, width="150")
	private TipoPendencia tipoPendencia;

	@FilterFieldOption(label = "Situação Pendência", property = "situacaoPendencia", labelProperty="situacaoPendencia.desc", type = FieldType.ENUM, width="145")
	private DominioSituacaoPendencia situacaoPendencia;

	@FilterFieldOption(label = "Data Regularização", property = "dataRegularizacao", formatter=DateFormatter.class,type = FieldType.DATE, width="145")
	private Date dataRegularizacao;

	public OcorrenciaCriteria() {
		super(Ocorrencia.class);
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
