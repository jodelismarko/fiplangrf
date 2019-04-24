package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoDetalhamentoDespesa extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 1136515049020936524L;

	public AcessoDetalhamentoDespesa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/detalhamentodespesa/pesquisar**", "CONSULTAR_DETALHAMENTO_DESPESA");
		hasAnyRole("/detalhamentodespesa/visualizar/**", "CONSULTAR_DETALHAMENTO_DESPESA");
		hasAnyRole("/detalhamentodespesa/incluir/**", "INCLUIR_DETALHAMENTO_DESPESA");
		hasAnyRole("/detalhamentodespesa/alterar/**", "ALTERAR_DETALHAMENTO_DESPESA");
		hasAnyRole("/detalhamentodespesa/excluir/**", "EXCLUIR_DETALHAMENTO_DESPESA");
		hasAnyRole("/detalhamentodespesa/visualizarHistorico/**", "CONSULTAR_DETALHAMENTO_DESPESA");
	}
}