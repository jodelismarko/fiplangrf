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

	public String getMensagemConfirmarEnviarRegularizacao() {
		return DominioMensagem.MSG_ENVIAR_PARA_REGULARIZACAO.getDesc();
	}
	
	public String getMensagemConfirmarEnviarAnalise() {
		return DominioMensagem.MSG_ENVIAR_PARA_ANALISE.getDesc();
	}
	
	public String getMensagemConfirmarFinalizar() {
		return DominioMensagem.MSG_FINALIZAR_OCORRENCIA.getDesc();
	}
}