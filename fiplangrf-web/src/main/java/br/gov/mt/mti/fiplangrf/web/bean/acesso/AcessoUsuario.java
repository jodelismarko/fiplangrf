package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoUsuario extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -4359724542264133849L;

	public AcessoUsuario(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/usuario/pesquisar**", "CONSULTAR_USUARIO");
		hasAnyRole("/usuario/visualizar/**", "CONSULTAR_USUARIO");
		hasAnyRole("/usuario/incluir/**", "INCLUIR_USUARIO");
		hasAnyRole("/usuario/alterar/**", "ALTERAR_USUARIO");
		hasAnyRole("/usuario/vincular/**","ALTERAR_USUARIO");
		hasAnyRole("/usuario/excluir/**", "EXCLUIR_USUARIO");
		hasAnyRole("/usuario/visualizarHistorico/**", "CONSULTAR_USUARIO");
		hasAnyRoleRegex("/perfil/pesquisar/popup/([^/].*?)/(\\d*)\\??.*", "CONSULTAR_PERFIL");
	}
}