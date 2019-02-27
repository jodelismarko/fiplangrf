package br.gov.mt.mti.fiplangrf.service.security.interfaces;

import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public interface UsuarioLoginSvcRemote {
	public Usuario loadByCodigo(Long codigo);
	public Usuario findByEmail(String email);
	public Usuario findByLogin(String login);
	public FIPLANUsuario findFIPLANUsuarioByLogin(String cpf);
}
