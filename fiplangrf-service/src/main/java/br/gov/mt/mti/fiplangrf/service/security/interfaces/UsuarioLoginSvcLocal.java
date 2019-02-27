package br.gov.mt.mti.fiplangrf.service.security.interfaces;

import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public interface UsuarioLoginSvcLocal extends UsuarioLoginSvcRemote {
	public void updateDataLogin(Usuario entity, String ipLogin);
	public void updateDataLoginNULL(Usuario entity);
}
