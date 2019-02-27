package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoTipoOcorrencia extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 6879710370164170706L;

	public AcessoTipoOcorrencia(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/tipoocorrencia/pesquisar**", "CONSULTAR_TIPO_OCORRENCIA");
		hasAnyRole("/tipoocorrencia/visualizar/**", "CONSULTAR_TIPO_OCORRENCIA");
		hasAnyRole("/tipoocorrencia/incluir/**", "INCLUIR_TIPO_OCORRENCIA");
		hasAnyRole("/tipoocorrencia/alterar/**", "ALTERAR_TIPO_OCORRENCIA");
		hasAnyRole("/tipoocorrencia/excluir/**", "EXCLUIR_TIPO_OCORRENCIA");
		hasAnyRole("/tipoocorrencia/visualizarHistorico/**", "CONSULTAR_TIPO_OCORRENCIA");
	}
}