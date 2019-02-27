package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoCNAE extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 4862945369768192342L;

	public AcessoCNAE(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/cnae/pesquisar**", "CONSULTAR_CNAE");
		hasAnyRole("/cnae/visualizar/**", "CONSULTAR_CNAE");
		hasAnyRole("/cnae/incluir/**", "INCLUIR_CNAE");
		hasAnyRole("/cnae/alterar/**", "ALTERAR_CNAE");
		hasAnyRole("/cnae/excluir/**", "EXCLUIR_CNAE");
		hasAnyRole("/cnae/visualizarHistorico/**", "CONSULTAR_CNAE");
	}
}