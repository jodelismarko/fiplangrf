package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.Serializable;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoCNAE;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoFuncionalidade;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoGrupoPendencia;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoNaturezaJuridica;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoOcorrencia;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoPerfil;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoRevisao;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoSubGrupoPendencia;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoTipoAdministracao;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoTipoOcorrencia;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoTipoPendencia;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoUnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoUsuario;

public class ControleAcessoWeb extends AbstractControleAcessoWeb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9205675260015011072L;

	public ControleAcessoWeb(final HttpSecurity http) {
		super(http);
	}

	public void aplicar() throws Exception {
		
		hasAnyRole("/audit/log**", "AUDIT_LOG");
		
		hasAnyRole("/relatorioExemploBean/relatorio", "RELATORIO_EXEMPLO");
		hasAnyRole("/relatorioExemploBean/" + Constantes.RELATORIO_EXIBIR_URL, "RELATORIO_EXEMPLO");		
		
		new AcessoUsuario(http).aplicar();
		new AcessoFuncionalidade(http).aplicar();
		new AcessoPerfil(http).aplicar();
		new AcessoCNAE(http).aplicar();
		new AcessoNaturezaJuridica(http).aplicar();
		new AcessoTipoOcorrencia(http).aplicar();
		new AcessoTipoAdministracao(http).aplicar();
		new AcessoGrupoPendencia(http).aplicar();
		new AcessoSubGrupoPendencia(http).aplicar();
		new AcessoTipoPendencia(http).aplicar();
		new AcessoUnidadeAdministrativa(http).aplicar();
		new AcessoOcorrencia(http).aplicar();
		new AcessoRevisao(http).aplicar();
	}
}
