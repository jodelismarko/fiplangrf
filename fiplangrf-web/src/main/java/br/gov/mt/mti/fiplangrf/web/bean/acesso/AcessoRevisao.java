package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoRevisao extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -4359724542264133849L;

	public AcessoRevisao(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/revisao/pesquisar**", "CONSULTAR_REVISAO");
	}
}