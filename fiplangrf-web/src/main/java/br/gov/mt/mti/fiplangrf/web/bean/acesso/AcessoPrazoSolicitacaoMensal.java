package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoPrazoSolicitacaoMensal extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = 6879710370164170706L;

	public AcessoPrazoSolicitacaoMensal(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/prazosolicitacaomensal/pesquisar**", "CONSULTAR_PRAZO_SOLICITACAO_MENSAL");
		hasAnyRole("/prazosolicitacaomensal/visualizar/**", "CONSULTAR_PRAZO_SOLICITACAO_MENSAL");
		hasAnyRole("/prazosolicitacaomensal/incluir/**", "INCLUIR_PRAZO_SOLICITACAO_MENSAL");
		hasAnyRole("/prazosolicitacaomensal/alterar/**", "ALTERAR_PRAZO_SOLICITACAO_MENSAL");
		hasAnyRole("/prazosolicitacaomensal/excluir/**", "EXCLUIR_PRAZO_SOLICITACAO_MENSAL");
		hasAnyRole("/prazosolicitacaomensal/visualizarHistorico/**", "CONSULTAR_PRAZO_SOLICITACAO_MENSAL");
	}
}