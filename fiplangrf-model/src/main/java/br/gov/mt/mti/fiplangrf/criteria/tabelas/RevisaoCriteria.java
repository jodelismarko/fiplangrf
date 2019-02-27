package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import java.util.Date;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Revisao;
import br.gov.mt.mti.fiplangrf.util.formatter.DateTimeFormatter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class RevisaoCriteria extends DynamicSearchCriteria<Revisao> {

	@FilterFieldOption(label = "Data Revisão", property = "dataRevisao", formatter = DateTimeFormatter.class, type = FieldType.DATE)
	private Date dataRevisao;

	@FilterFieldOption(label = "Usuário", property = "usuario.fiplanUsuario.nome", labelProperty = "usuario.fiplanUsuario.nome", type = FieldType.TEXT, join = "usuario.fiplanUsuario", fieldVisibility = FieldVisibility.FILTER)
	private String nomeFiltro;

	@FilterFieldOption(label = "Usuário", property = "usuario", labelProperty = "usuario.fiplanUsuario.nome", type = FieldType.TEXT, join = "usuario", fieldVisibility = FieldVisibility.GRID)
	private String nomeUsuario;

	@FilterFieldOption(label = "CPF", property = "usuario", labelProperty = "usuario.cpf", type = FieldType.TEXT, join = "usuario", fieldVisibility = FieldVisibility.GRID)
	private String cpf;

	@FilterFieldOption(label = "IP ", property = "enderecoIP", type = FieldType.TEXT)
	private String enderecoIP;

	public RevisaoCriteria() {
		super(Revisao.class);
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
