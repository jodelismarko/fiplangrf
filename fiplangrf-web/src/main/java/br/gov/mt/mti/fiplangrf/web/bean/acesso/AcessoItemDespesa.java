package br.gov.mt.mti.fiplangrf.web.bean.acesso;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.web.bean.AbstractControleAcessoWeb;

public class AcessoItemDespesa extends AbstractControleAcessoWeb {
	
	private static final long serialVersionUID = 4638868820368373723L;

	public AcessoItemDespesa(HttpSecurity http) {
		super(http);
	}

	@Override
	public void aplicar() throws Exception {
		hasAnyRole("/itemdespesa/pesquisar**", "CONSULTAR_ITEM_DESPESA");
		hasAnyRole("/itemdespesa/visualizar/**", "CONSULTAR_ITEM_DESPESA");
		hasAnyRole("/itemdespesa/incluir/**", "INCLUIR_ITEM_DESPESA");
		hasAnyRole("/itemdespesa/alterar/**", "ALTERAR_ITEM_DESPESA");
		hasAnyRole("/itemdespesa/excluir/**", "EXCLUIR_ITEM_DESPESA");
		hasAnyRole("/itemdespesa/visualizarHistorico/**", "CONSULTAR_ITEM_DESPESA");
	}
}
