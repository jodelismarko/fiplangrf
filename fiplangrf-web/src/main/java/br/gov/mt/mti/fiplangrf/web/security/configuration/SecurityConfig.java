package br.gov.mt.mti.fiplangrf.web.security.configuration;

import java.util.Arrays;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.service.security.interfaces.UsuarioLoginSvcLocal;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioLoginService;
import br.gov.mt.mti.fiplangrf.web.bean.ControleAcessoWeb;
import br.gov.mt.mti.fiplangrf.web.security.handler.AccessDeniedImpl;
import br.gov.mt.mti.fiplangrf.web.security.handler.AuthenticationFailureImpl;
import br.gov.mt.mti.fiplangrf.web.security.handler.AuthenticationSuccessImpl;
import br.gov.mt.mti.fiplangrf.web.security.handler.CustomAuthenticationEntryPoint;
import br.gov.mt.mti.fiplangrf.web.security.provider.CustomAuthenticationProvider;
import br.gov.mt.mti.fiplangrf.web.security.service.UserDetailService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements Constantes {

	protected Log logger = LogFactory.getLog(this.getClass());

	private static final String JNDI_EJB_USUARIO_SVC_LOCAL = "java:app/fiplangrf-ejb/"
			+ UsuarioLoginService.class.getSimpleName() + "!" + UsuarioLoginSvcLocal.class.getName();
	
	protected UsuarioLoginSvcLocal usuarioSvcLocal = null;
	
	@Autowired
	AuthenticationSuccessImpl authenticationSuccessHandler;
	
	@Autowired
	AuthenticationFailureImpl authenticationFailureHandler;
	
	@Autowired
	AccessDeniedImpl accessDeniedImpl;

	@Autowired
	private CustomAuthenticationProvider authProvider;	

	@Autowired
	UserDetailService userDetailService;


	@Bean(name = "usuarioSvcLocal")
	public UsuarioLoginSvcLocal usuarioSvcLocal() {		
		JndiTemplate jndi = new JndiTemplate();
		try {
			usuarioSvcLocal = (UsuarioLoginSvcLocal) jndi.lookup(JNDI_EJB_USUARIO_SVC_LOCAL);
		} catch (NamingException e) {
			logger.error("NamingException for " + JNDI_EJB_USUARIO_SVC_LOCAL, e);
		}
		return usuarioSvcLocal;
	}


	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		if(EnvUtil.getAmbiente().equals("des")) {
			inMemoryConfigurer()
	        	.withUser("999.999.999-99")
	            .password("welcome1")
	            .authorities("ROLE_DEVELOPMENT")
	            .and()
	            .configure(auth);
		}
		
		authProvider.setUserDetailsService(userDetailService);
		auth.authenticationProvider(authProvider);

	}

	private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryConfigurer() {
		return new InMemoryUserDetailsManagerConfigurer<>();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));		
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
				
		configuration.setAllowedHeaders(Arrays.asList("Content-Type","Access-Control-Allow-Origin", MTIFMW_ACTION, X_CSRF_TOKEN));
		
		return source;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*Configurar o mais específico primeiro, depois o mais genérico*/
		http.headers().frameOptions().sameOrigin();
		
		http.cors().disable();
		
		http.authorizeRequests()							
				.antMatchers("/resources/**","/javax.faces.resource/**", "/init").permitAll()						
				.antMatchers("/loginFailed","/maxSession","/public/**").permitAll()
				.antMatchers("/acessoNegadoPopup","/error.jsf","/acessoNegado","/sys/logout","/home", "/homePopup", "/index.jsp","/access-denied-popup.jsf", "/DownloadServlet", "/DownloadExcelServlet").authenticated();
		
		http.csrf().ignoringAntMatchers("/loginFailed","/maxSession","/public/**"
				                       ,"/acessoNegadoPopup","/error.jsf","/acessoNegado","/sys/logout"
				                       ,"/home","/homePopup", "/index.jsp","/resources/**","/javax.faces.resource/**", "/init");
		
		ControleAcessoWeb controleAcesso = new ControleAcessoWeb(http);
		controleAcesso.aplicar();
		
		http.authorizeRequests().antMatchers("/dev/**").hasRole("DEVELOPMENT");
				
		http.authorizeRequests()
				.anyRequest().denyAll()
				.and().formLogin().loginPage("/login").permitAll()
				.usernameParameter("login").passwordParameter("senha")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and().sessionManagement().maximumSessions(1)
				.expiredUrl("/login").maxSessionsPreventsLogin(false);
		http.exceptionHandling().accessDeniedHandler(accessDeniedImpl);
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		http.logout().deleteCookies("JSESSIONID");		
		http.authorizeRequests().antMatchers("/**").authenticated();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/javax.faces.resource/**");		
		super.configure(web);
	}
}