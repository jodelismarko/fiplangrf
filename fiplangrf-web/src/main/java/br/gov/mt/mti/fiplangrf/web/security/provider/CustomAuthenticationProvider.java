package br.gov.mt.mti.fiplangrf.web.security.provider;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.mti.fiplangrf.web.security.authentication.FIPLANAuthenticationClient;
import br.gov.mt.mti.fiplangrf.web.security.authentication.FIPLANAuthenticationResponse;
import br.gov.mt.mti.fiplangrf.web.security.authentication.FIPLANBadCredentialsException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	private UserDetailsService userDetailsService;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails user = null;
		FIPLANAuthenticationResponse resp = null;

		LOGGER.info("Ambiente: " + EnvUtil.getAmbiente());

		if (name == null || name.trim().isEmpty()) {
			throw new FIPLANBadCredentialsException("Informe o CPF.");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new FIPLANBadCredentialsException("Informe a senha.");
		}

		try {

			// MODO DESENVOLVEDOR
			if (EnvUtil.getAmbiente().equals("des")) {
				LOGGER.warn("Modo de Desenvolvimento: Autenticação ignorada. ");
				try {
					user = retrieveUser(name, (UsernamePasswordAuthenticationToken) authentication);
				} catch (UsernameNotFoundException notFound) {
					throw new FIPLANBadCredentialsException(
							"Acesso negado. Por favor, contate o administrador do sistema.");
				}
				return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
			}

			// MODO NORMAL
			// usar as credenciais
			// e autenticar no servico do FIPLAN
			resp = FIPLANAuthenticationClient.authenticate(name, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw new FIPLANBadCredentialsException("Não foi possível conectar ao serviço de autenticação do FIPLAN.");
		}

		if (resp.getCodigoResponse() == 2) {
			throw new FIPLANBadCredentialsException(resp.getMsg());
		}

		try {
			user = retrieveUser(name, (UsernamePasswordAuthenticationToken) authentication);
		}

		catch (UsernameNotFoundException notFound) {
			throw new FIPLANBadCredentialsException("Acesso negado. Por favor, contate o administrador do sistema.");
		}

		catch (Exception e) {
			throw new FIPLANBadCredentialsException(e.getMessage());
		}

		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
		} catch (UsernameNotFoundException notFound) {

			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}