package br.gov.mt.mti.fiplangrf.web.util;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;

@Named("helperBean")
@ViewScoped
public class HelperBean {

	public String getMensagemHeaderExclusao() {
		return DominioMensagem.MSG_TITULO_EXCLUSAO.getDesc();
	}

	public String getMensagemConfirmarExclusao() {
		return DominioMensagem.MSG_CONFIRMAR_EXCLUSAO.getDesc();
	}

}