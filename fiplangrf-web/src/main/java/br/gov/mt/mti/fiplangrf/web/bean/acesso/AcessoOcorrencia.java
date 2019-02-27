package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoOcorrencia extends AbstractControleAcessoWeb {
	
	private static final long serialVersionUID = 4638868820368373723L;

	public AcessoOcorrencia(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/ocorrencia/pesquisar**", "CONSULTAR_OCORRENCIA");
		hasAnyRole("/ocorrencia/visualizar/**", "CONSULTAR_OCORRENCIA");
		hasAnyRole("/ocorrencia/incluir/**", "INCLUIR_OCORRENCIA");
		hasAnyRole("/ocorrencia/alterar/**", "ALTERAR_OCORRENCIA");
		hasAnyRole("/ocorrencia/excluir/**", "EXCLUIR_OCORRENCIA");
		hasAnyRole("/ocorrencia/visualizarHistorico/**", "CONSULTAR_OCORRENCIA");
	}
}
