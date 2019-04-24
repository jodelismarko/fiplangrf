package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoFonteRecurso extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -4359724542264133849L;

	public AcessoFonteRecurso(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/fonterecurso/pesquisar**", "CONSULTAR_FONTE_RECURSO");
		hasAnyRole("/fonterecurso/visualizar/**", "CONSULTAR_FONTE_RECURSO");
		hasAnyRole("/fonterecurso/incluir/**", "INCLUIR_FONTE_RECURSO");
		hasAnyRole("/fonterecurso/alterar/**", "ALTERAR_FONTE_RECURSO");
		hasAnyRole("/fonterecurso/excluir/**", "EXCLUIR_FONTE_RECURSO");
		hasAnyRole("/fonterecurso/visualizarHistorico/**", "CONSULTAR_FONTE_RECURSO");
	}
}