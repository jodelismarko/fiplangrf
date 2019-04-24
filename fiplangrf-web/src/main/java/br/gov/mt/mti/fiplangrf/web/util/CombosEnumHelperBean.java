package br.gov.mt.mti.fiplangrf.web.util;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.gov.mt.cepromat.ceprofw.core.dominio.DominioFormatoSaidaRelatorio;
import br.gov.mt.mti.fiplangrf.dominio.DominioIndicativoProvisao;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.dominio.DominioNaturezaDespesa;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTetoFinanceiroFiplan;
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
	
	public DominioSituacaoRegistro[] getDominioSituacaoRegistro() {
		return DominioSituacaoRegistro.values();
	}
	
	public DominioTipoPermissao[] getDominioTipoPermissao() {
		return DominioTipoPermissao.values();
	}
	
	public DominioIndicativoProvisao[] getDominioIndicativoProvisao() {
		return DominioIndicativoProvisao.values();
	}
	
	public DominioMensagem[] getDominioMensagem() {
		return DominioMensagem.values();
	}
	
	public DominioMes[] getDominioMes() {
		return DominioMes.values();
	}
	
	public DominioNaturezaDespesa[] getDominioNaturezaDespesa() {
		return DominioNaturezaDespesa.values();
	}
	
	public DominioTetoFinanceiroFiplan[] getDominioTetoFinanceiroFiplan() {
		return DominioTetoFinanceiroFiplan.values();
	}

}