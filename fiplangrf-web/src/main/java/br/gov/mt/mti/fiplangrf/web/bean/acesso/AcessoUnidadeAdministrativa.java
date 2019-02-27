package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoUnidadeAdministrativa extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 4682229680916684821L;

	public AcessoUnidadeAdministrativa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/unidadeadministrativa/pesquisar**", "CONSULTAR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/unidadeadministrativa/visualizar/**", "CONSULTAR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/unidadeadministrativa/incluir/**", "INCLUIR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/unidadeadministrativa/alterar/**", "ALTERAR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/unidadeadministrativa/excluir/**", "EXCLUIR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/unidadeadministrativa/visualizarHistorico/**", "CONSULTAR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/cnaeunidadeadministrativa/visualizarHistorico/**", "CONSULTAR_UNIDADE_ADMINISTRATIVA");
		hasAnyRole("/legislacaounidadeadministrativa/visualizarHistorico/**", "CONSULTAR_UNIDADE_ADMINISTRATIVA");
	}
}