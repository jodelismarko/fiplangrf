package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoPerfil extends AbstractControleAcessoWeb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4359724542264133849L;

	public AcessoPerfil(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/perfil/pesquisar**", "CONSULTAR_PERFIL");
		hasAnyRole("/perfil/visualizar/**", "CONSULTAR_PERFIL");
		hasAnyRole("/perfil/incluir/**", "INCLUIR_PERFIL");
		hasAnyRole("/perfil/alterar/**", "ALTERAR_PERFIL");
		hasAnyRole("/perfil/vincular/**","VINCULAR_PERFIL_FUNCIONALIDADE");
		hasAnyRole("/perfil/excluir/**", "EXCLUIR_PERFIL");
		hasAnyRole("/perfil/visualizarHistorico/**", "CONSULTAR_PERFIL");
	}
}
