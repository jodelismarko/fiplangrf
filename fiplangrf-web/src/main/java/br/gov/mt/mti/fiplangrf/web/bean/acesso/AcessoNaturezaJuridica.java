package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoNaturezaJuridica extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 5423860977159653772L;

	public AcessoNaturezaJuridica(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/naturezajuridica/pesquisar**", "CONSULTAR_NATUREZA_JURIDICA");
		hasAnyRole("/naturezajuridica/visualizar/**", "CONSULTAR_NATUREZA_JURIDICA");
		hasAnyRole("/naturezajuridica/incluir/**", "INCLUIR_NATUREZA_JURIDICA");
		hasAnyRole("/naturezajuridica/alterar/**", "ALTERAR_NATUREZA_JURIDICA");
		hasAnyRole("/naturezajuridica/excluir/**", "EXCLUIR_NATUREZA_JURIDICA");
		hasAnyRole("/naturezajuridica/visualizarHistorico/**", "CONSULTAR_NATUREZA_JURIDICA");

	}
}
