package br.gov.mt.mti.fiplangrf.web.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.model.security.user.Permissao;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcLocal;
import br.gov.mt.mti.fiplangrf.web.security.user.UserDetail;

@Component
public class UserDetailService implements UserDetailsService {
	protected Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	UsuarioLoginSvcLocal usuarioSvcLocal;

	@Override
	public UserDetail loadUserByUsername(String userName) throws UsernameNotFoundException {
		FIPLANUsuario usuario = null;
		String cpfSanitized = userName.replaceAll("[^0-9]", "");
		cpfSanitized = Long.valueOf(cpfSanitized).toString();

		try {
			usuario = usuarioSvcLocal.findFIPLANUsuarioByLogin(cpfSanitized);
			if (usuario == null) {
				// Obs: No processo de autenticação, nunca revele que um determinado usuário não
				// existe. O melhor é dar a msg abaixo.
				throw new UsernameNotFoundException("Username ou senha inválidos.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Erro no banco de dados. Contacte o administrador do sistema.");
		}
		return obterUsuario(usuario);
	}

	public UserDetail obterUsuario(FIPLANUsuario usuarioFiplan) {

		Usuario usuario = usuarioSvcLocal.findByLogin(usuarioFiplan.getLogin());
		if (usuario == null) {
			// Obs: No processo de autenticação, nunca revele que um determinado usuário não
			// existe. O melhor é dar a msg abaixo.
			throw new UsernameNotFoundException("Acesso negado. Por favor, contate o administrador do sistema.");
		}

		String login = usuario.getLogin();

		boolean enabled = usuario.getSituacao().equals(DominioSituacao.ATIVO);
		boolean accountNonExpired = true;
		boolean credentialNonExpired = true;
		boolean accountNonLocked = true;

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		Map<String, String> permissaoItemMenu;

		permissaoItemMenu = new HashMap<String, String>();
		Map<String, String> funcionalidade = new HashMap<String, String>();

		for (Perfil perfil : usuario.getPerfis()) {
			for (Funcionalidade func : perfil.getFuncionalidades()) {

				if (func.getSituacao().equals(DominioSituacao.INATIVO))
					continue;

				authorities.add(new SimpleGrantedAuthority(func.getNome()));
				funcionalidade.put(func.getNome(), func.getNome());
				for (Permissao permissao : func.getPermissoes()) {
					if (permissao.getTipoPermissao().equals(DominioTipoPermissao.GATILHO)) {
						permissaoItemMenu.put(permissao.getAction().toLowerCase(), permissao.getNome());
					}
				}
			}
		}

		UserDetail userDetail = new UserDetail(login, "", enabled, accountNonExpired, credentialNonExpired,
				accountNonLocked, authorities);

		userDetail.setPermissaoItemMenu(permissaoItemMenu);
		userDetail.setNome(usuario.getFiplanUsuario().getNome());
		userDetail.setEmail(usuario.getFiplanUsuario().getEmail());
		userDetail.setCodigo(usuario.getCodigo());

		if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
			userDetail.setCpf("99999999999");
		} else {
			userDetail.setCpf(usuario.getCpf());
		}

		if (FacesContext.getCurrentInstance() != null) {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			userDetail.setIp(request.getRemoteAddr());
		}

		if (usuario instanceof Usuario) {
			Usuario user = (Usuario) usuario;

			logger.debug(user);
		}

		return userDetail;
	}
}