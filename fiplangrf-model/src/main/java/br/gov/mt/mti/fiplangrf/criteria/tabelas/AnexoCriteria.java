package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public class AnexoCriteria extends DynamicSearchCriteria<Anexo> {

	@FilterFieldOption(label = "Nome do Documento", property = "nomeAnexo", type = FieldType.TEXT)
	private String nomeAnexo;

	@FilterFieldOption(label = "Descrição", property = "descricao", type = FieldType.TEXT)
	private String descricao;

	@FilterFieldOption(label = "Conectividade", property = "conectividade", type = FieldType.TEXT)
	private String conectividade;

	@FilterFieldOption(label = "Usuario ", property = "usuario", type = FieldType.TEXT)
	private Usuario usuario;

	@FilterFieldOption(label = "Arquivo Anexo ", property = "arquivoAnexo", type = FieldType.LIST)
	private ArquivoAnexo arquivoAnexo;

	public AnexoCriteria() {
		super(Anexo.class);
	}

	public String getNomeAnexo() {
		return nomeAnexo;
	}

	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConectividade() {
		return conectividade;
	}

	public void setConectividade(String conectividade) {
		this.conectividade = conectividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArquivoAnexo getArquivoAnexo() {
		return arquivoAnexo;
	}

	public void setArquivoAnexo(ArquivoAnexo arquivoAnexo) {
		this.arquivoAnexo = arquivoAnexo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("nomeAnexo", nomeAnexo)
			.append("descricao", descricao)
			.append("conectividade", conectividade)
			.append("usuario", usuario)
			.append("arquivoAnexo", arquivoAnexo)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
