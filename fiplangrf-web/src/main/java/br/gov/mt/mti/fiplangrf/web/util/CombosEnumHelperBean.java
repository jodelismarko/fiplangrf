package br.gov.mt.mti.fiplangrf.web.util;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.gov.mt.cepromat.ceprofw.core.dominio.DominioFormatoSaidaRelatorio;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;

@Named("combosEnumHelperBean")
@ViewScoped
public class CombosEnumHelperBean {

	public DominioFormatoSaidaRelatorio[] getDominioFormatoSaidaTodos() {
		return DominioFormatoSaidaRelatorio.values();
	}

	public DominioFormatoSaidaRelatorio[] getDominioFormatoSaidaPdfXls() {
		return new DominioFormatoSaidaRelatorio[] { DominioFormatoSaidaRelatorio.PDF,
				DominioFormatoSaidaRelatorio.XLS };

	}
	
	public DominioSimNao[] getDominioSimNao() {
		return DominioSimNao.values();
	}
	
	public DominioSituacao[] getDominioSituacao() {
		return DominioSituacao.values();
	}
	
	public DominioTipoPermissao[] getDominioTipoPermissao() {
		return DominioTipoPermissao.values();
	}
	


}