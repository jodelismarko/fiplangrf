package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoTipoPendencia extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 4440954963339722278L;

	public AcessoTipoPendencia(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/tipopendencia/pesquisar**", "CONSULTAR_TIPO_PENDENCIA");
		hasAnyRole("/tipopendencia/visualizar/**", "CONSULTAR_TIPO_PENDENCIA");
		hasAnyRole("/tipopendencia/incluir/**", "INCLUIR_TIPO_PENDENCIA");
		hasAnyRole("/tipopendencia/alterar/**", "ALTERAR_TIPO_PENDENCIA");
		hasAnyRole("/tipopendencia/excluir/**", "EXCLUIR_TIPO_PENDENCIA");
		hasAnyRole("/tipopendencia/visualizarHistorico/**", "CONSULTAR_TIPO_PENDENCIA");
	}
}