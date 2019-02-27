package br.gov.mt.mti.fiplangrf.util;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;
import br.gov.mt.mti.fiplangrf.common.util.Env;

@Named
public class UsuarioLogadoProducer {
	
	@Produces
	public UsuarioLogado getUsuarioLogado(){
		return Env.getUsuarioLogado();
	}
}
