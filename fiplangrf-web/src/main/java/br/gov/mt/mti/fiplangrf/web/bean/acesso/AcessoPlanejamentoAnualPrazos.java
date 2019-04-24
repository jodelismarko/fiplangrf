package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoPlanejamentoAnualPrazos extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -30607049287499105L;

	public AcessoPlanejamentoAnualPrazos(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/planejamentoanualprazos/pesquisar**", "CONSULTAR_PLANEJAMENTO_ANUAL_PRAZOS");
		hasAnyRole("/planejamentoanualprazos/visualizar/**", "CONSULTAR_PLANEJAMENTO_ANUAL_PRAZOS");
		hasAnyRole("/planejamentoanualprazos/incluir/**", "INCLUIR_PLANEJAMENTO_ANUAL_PRAZOS");
		hasAnyRole("/planejamentoanualprazos/alterar/**", "ALTERAR_PLANEJAMENTO_ANUAL_PRAZOS");
		hasAnyRole("/planejamentoanualprazos/excluir/**", "EXCLUIR_PLANEJAMENTO_ANUAL_PRAZOS");
		hasAnyRole("/planejamentoanualprazos/visualizarHistorico/**", "CONSULTAR_PLANEJAMENTO_ANUAL_PRAZOS");
	}
}