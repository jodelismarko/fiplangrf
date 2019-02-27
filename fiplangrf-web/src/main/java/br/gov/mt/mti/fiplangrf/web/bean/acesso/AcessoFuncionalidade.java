package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoFuncionalidade extends AbstractControleAcessoWeb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4359724542264133849L;

	public AcessoFuncionalidade(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRoleRegex("/funcionalidade/pesquisar/popup/([^/].*?)/(\\d*)", "CONSULTAR_FUNCIONALIDADE");		
		hasAnyRole("/funcionalidade/pesquisar**", "CONSULTAR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/visualizar/**", "CONSULTAR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/incluir", "INCLUIR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/alterar/**", "ALTERAR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/excluir/**", "EXCLUIR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/vincular/**", "VINCULAR_FUNCIONALIDADE");
		hasAnyRole("/funcionalidade/visualizarHistorico/**", "CONSULTAR_FUNCIONALIDADE");
	}
}