package br.gov.mt.mti.fiplangrf.criteria.user;

import java.util.Date;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.util.formatter.DateTimeFormatter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioCriteria extends DynamicSearchCriteria<Usuario> {

	@FilterFieldOption(label = "CPF", property = "login", type = FieldType.TEXT, fieldVisibility = FieldVisibility.FILTER)
	private String login;

	@FilterFieldOption(label = "CPF", property = "cpf", type = FieldType.TEXT, fieldVisibility = FieldVisibility.GRID, width = "120")
	private String cpfFormatado;

	@FilterFieldOption(label = "Nome", property = "fiplanUsuario.nome", type = FieldType.TEXT, width = "300")
	private String nome;

	@FilterFieldOption(label = "Email", property = "fiplanUsuario.email", type = FieldType.TEXT, fieldVisibility = FieldVisibility.FILTER)
	private String email;

	@FilterFieldOption(label = "Situação", property = "situacao", labelProperty = "situacao.desc", type = FieldType.ENUM, dataSource = DominioSituacao.class, width = "90")
	private DominioSituacao situacao;

	@FilterFieldOption(label = "Data Último Acesso", property = "dataUltimoAcesso", type = FieldType.DATE, width = "160", formatter = DateTimeFormatter.class)
	private Date dataUltimoAcesso;

	public UsuarioCriteria() {
		super(Usuario.class);
	}

	@Override
	public boolean isSelectDistinct() {
		return true;
	}
}
