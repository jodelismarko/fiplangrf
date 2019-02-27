package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoGrupoPendencia extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 1136515049020936524L;

	public AcessoGrupoPendencia(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/grupopendencia/pesquisar**", "CONSULTAR_GRUPO_PENDENCIA");
		hasAnyRole("/grupopendencia/visualizar/**", "CONSULTAR_GRUPO_PENDENCIA");
		hasAnyRole("/grupopendencia/incluir/**", "INCLUIR_GRUPO_PENDENCIA");
		hasAnyRole("/grupopendencia/alterar/**", "ALTERAR_GRUPO_PENDENCIA");
		hasAnyRole("/grupopendencia/excluir/**", "EXCLUIR_GRUPO_PENDENCIA");
		hasAnyRole("/grupopendencia/visualizarHistorico/**", "CONSULTAR_GRUPO_PENDENCIA");
	}
}