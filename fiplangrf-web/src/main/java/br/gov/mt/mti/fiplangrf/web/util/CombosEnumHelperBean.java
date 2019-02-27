package br.gov.mt.mti.fiplangrf.web.util;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.gov.mt.cepromat.ceprofw.core.dominio.DominioFormatoSaidaRelatorio;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNaoFIPLAN;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastralCnpj;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoAtividade;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoLegislacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNaturezaJuridica;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;

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
	
	public DominioTipoOperacao[] getDominioTipoOperacao() {
		return DominioTipoOperacao.values();
	}
	
	public DominioSimNao[] getDominioSimNao() {
		return DominioSimNao.values();
	}

	public DominioSimNaoFIPLAN[] getDominioSimNaoFIPLAN() {
		return DominioSimNaoFIPLAN.values();
	}
	
	public DominioSituacao[] getDominioSituacao() {
		return DominioSituacao.values();
	}
	
	public DominioSituacaoCadastralCnpj[] getDominioSituacaoCadastralCnpj() {
		return DominioSituacaoCadastralCnpj.values();
	}
	
	public DominioSituacaoPendencia[] getDominioSituacaoPendencia() {
		return DominioSituacaoPendencia.values();
	}
	
	public DominioTipoLegislacao[] getDominioTipoLegislacao() {
		return DominioTipoLegislacao.values();
	}
	
	public DominioTipoNaturezaJuridica[] getDominioTipoNaturezaJuridica() {
		return DominioTipoNaturezaJuridica.values();
	}
	
	public DominioTipoPermissao[] getDominioTipoPermissao() {
		return DominioTipoPermissao.values();
	}
	
	public DominioTipoPoder[] getDominioTipoPoder() {
		return DominioTipoPoder.values();
	}
	
	public DominioTipoAtividade[] getDominioTipoAtividade() {
		return DominioTipoAtividade.values();
	}

}