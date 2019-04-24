package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoDespesa extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 4862945369768192342L;

	public AcessoDespesa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/despesa/pesquisar**", "CONSULTAR_DESPESA");
		hasAnyRole("/despesa/visualizar/**", "CONSULTAR_DESPESA");
		hasAnyRole("/despesa/incluir/**", "INCLUIR_DESPESA");
		hasAnyRole("/despesa/alterar/**", "ALTERAR_DESPESA");
		hasAnyRole("/despesa/excluir/**", "EXCLUIR_DESPESA");
		hasAnyRole("/despesa/visualizarHistorico/**", "CONSULTAR_DESPESA");
	}
}