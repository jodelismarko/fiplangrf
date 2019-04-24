package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.Serializable;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoDespesa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoDetalhamentoDespesa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoDetalhamentoProvisaoDespesa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoFonteRecurso;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoFuncionalidade;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoGrupoControleDespesa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoItemDespesa;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoPerfil;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoPlanejamentoAnualPrazos;
import br.gov.mt.mti.fiplangrf.web.bean.acesso.AcessoPrazoSolicitacaoMensal;
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
		new AcessoDespesa(http).aplicar();
		new AcessoDetalhamentoProvisaoDespesa(http).aplicar();
		new AcessoPrazoSolicitacaoMensal(http).aplicar();
		new AcessoPlanejamentoAnualPrazos(http).aplicar();
		new AcessoDetalhamentoDespesa(http).aplicar();
		new AcessoGrupoControleDespesa(http).aplicar();
		new AcessoItemDespesa(http).aplicar();
		new AcessoFonteRecurso(http).aplicar();
	}
}
