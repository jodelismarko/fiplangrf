package br.gov.mt.mti.fiplangrf.common.util;

public enum ButtonScript {
	RETURN("return parentPopupClose()"),
	CLOSE_MAIN_MSG_DLG("PF('" + Constantes.MAIN_MSG_DIALOG + "').hide();return false"),
	ATUALIZAR_PESQUISA("window.parent.atualizarPesquisa();return window.parent.popup.close()"),
	SALVAR_ATUALIZAR_PESQUISA("window.parent.atualizarPesquisa();PF('" + Constantes.MAIN_MSG_DIALOG + "').hide();return false");

	String script;

	ButtonScript(String script) {
		this.script = script;
	}

	public String toString() {
		return this.script;
	}

}
