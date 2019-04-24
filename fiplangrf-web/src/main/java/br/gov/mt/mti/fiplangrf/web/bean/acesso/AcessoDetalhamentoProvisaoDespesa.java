package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoDetalhamentoProvisaoDespesa extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 5423860977159653772L;

	public AcessoDetalhamentoProvisaoDespesa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/detalhamentoprovisaodespesa/pesquisar**", "CONSULTAR_DETALHAMENTO_PROVISAO_DESPESA");
		hasAnyRole("/detalhamentoprovisaodespesa/visualizar/**", "CONSULTAR_DETALHAMENTO_PROVISAO_DESPESA");
		hasAnyRole("/detalhamentoprovisaodespesa/incluir/**", "INCLUIR_DETALHAMENTO_PROVISAO_DESPESA");
		hasAnyRole("/detalhamentoprovisaodespesa/alterar/**", "ALTERAR_DETALHAMENTO_PROVISAO_DESPESA");
		hasAnyRole("/detalhamentoprovisaodespesa/excluir/**", "EXCLUIR_DETALHAMENTO_PROVISAO_DESPESA");
		hasAnyRole("/detalhamentoprovisaodespesa/visualizarHistorico/**", "CONSULTAR_DETALHAMENTO_PROVISAO_DESPESA");

	}
}
