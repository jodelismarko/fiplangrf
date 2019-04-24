package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoGrupoControleDespesa extends AbstractControleAcessoWeb {

	private static final long serialVersionUID = -6559828684535654169L;

	public AcessoGrupoControleDespesa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/grupocontroledespesa/pesquisar**", "CONSULTAR_GRUPO_CONTROLE_DESPESA");
		hasAnyRole("/grupocontroledespesa/visualizar/**", "CONSULTAR_GRUPO_CONTROLE_DESPESA");
		hasAnyRole("/grupocontroledespesa/incluir/**", "INCLUIR_GRUPO_CONTROLE_DESPESA");
		hasAnyRole("/grupocontroledespesa/alterar/**", "ALTERAR_GRUPO_CONTROLE_DESPESA");
		hasAnyRole("/grupocontroledespesa/excluir/**", "EXCLUIR_GRUPO_CONTROLE_DESPESA");
		hasAnyRole("/grupocontroledespesa/visualizarHistorico/**", "CONSULTAR_GRUPO_CONTROLE_DESPESA");
	}
}