package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoTipoAdministracao extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -30607049287499105L;

	public AcessoTipoAdministracao(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/tipoadministracao/pesquisar**", "CONSULTAR_TIPO_ADMINISTRACAO");
		hasAnyRole("/tipoadministracao/visualizar/**", "CONSULTAR_TIPO_ADMINISTRACAO");
		hasAnyRole("/tipoadministracao/incluir/**", "INCLUIR_TIPO_ADMINISTRACAO");
		hasAnyRole("/tipoadministracao/alterar/**", "ALTERAR_TIPO_ADMINISTRACAO");
		hasAnyRole("/tipoadministracao/excluir/**", "EXCLUIR_TIPO_ADMINISTRACAO");
		hasAnyRole("/tipoadministracao/visualizarHistorico/**", "CONSULTAR_TIPO_ADMINISTRACAO");
	}
}