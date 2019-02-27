package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoSubGrupoPendencia extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -6559828684535654169L;

	public AcessoSubGrupoPendencia(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/subgrupopendencia/pesquisar**", "CONSULTAR_SUBGRUPO_PENDENCIA");
		hasAnyRole("/subgrupopendencia/visualizar/**", "CONSULTAR_SUBGRUPO_PENDENCIA");
		hasAnyRole("/subgrupopendencia/incluir/**", "INCLUIR_SUBGRUPO_PENDENCIA");
		hasAnyRole("/subgrupopendencia/alterar/**", "ALTERAR_SUBGRUPO_PENDENCIA");
		hasAnyRole("/subgrupopendencia/excluir/**", "EXCLUIR_SUBGRUPO_PENDENCIA");
		hasAnyRole("/subgrupopendencia/visualizarHistorico/**", "CONSULTAR_SUBGRUPO_PENDENCIA");
	}
}