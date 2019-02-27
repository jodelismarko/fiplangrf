package br.gov.mt.mti.fiplangrf.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import br.gov.mt.cepromat.ceprofw.common.util.EnvUtil;

public abstract class AbstractControleAcessoWeb implements Serializable {

	private static final long serialVersionUID = -9205675260015011072L;
	
	protected HttpSecurity http;
	
	public AbstractControleAcessoWeb(final HttpSecurity http){
		this.http = http;
	}
	
	public abstract void aplicar() throws Exception;
	
	public void hasAnyRoleRegex(String urlPattern, String ...roles) throws Exception {
		if(EnvUtil.getAmbiente().equals("des")) {
			List<String> lRoles = new ArrayList<String>(Arrays.asList(roles));
			lRoles.add("DEVELOPMENT");
			this.http.authorizeRequests().regexMatchers(urlPattern).hasAnyRole(lRoles.toArray(new String[] {}));
		} else 
			this.http.authorizeRequests().regexMatchers(urlPattern).hasAnyRole(roles);
	}
	
	public void hasAnyRole(String urlPattern, String ... roles) throws Exception {
		if(EnvUtil.getAmbiente().equals("des")) {
			List<String> lRoles = new ArrayList<String>(Arrays.asList(roles));
			lRoles.add("DEVELOPMENT");
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyRole(lRoles.toArray(new String[] {}));
		} else 
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyRole(roles);
		
	}
	
	public void hasRole(String urlPattern, String role) throws Exception {
		if(EnvUtil.getAmbiente().equals("des")) {
			List<String> lRoles = new ArrayList<String>();
			lRoles.add("DEVELOPMENT");
			lRoles.add(role);
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyRole(lRoles.toArray(new String[] {}));
		} else 
			this.http.authorizeRequests().antMatchers(urlPattern).hasRole(role);
	}
	
	public void hasAnyAuthority(String urlPattern, String ... authorities) throws Exception {
		if(EnvUtil.getAmbiente().equals("des")) {
			List<String> lAuthorities = new ArrayList<String>(Arrays.asList(authorities));
			lAuthorities.add("ROLE_DEVELOPMENT");
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyAuthority(lAuthorities.toArray(new String[] {}));
		} else 
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyAuthority(authorities);
		
	}
	
	public void hasAuthority(String urlPattern, String authority) throws Exception {
		if(EnvUtil.getAmbiente().equals("des")) {
			List<String> lAuthorities = new ArrayList<String>();
			lAuthorities.add("ROLE_DEVELOPMENT");
			lAuthorities.add(authority);
			this.http.authorizeRequests().antMatchers(urlPattern).hasAnyAuthority(lAuthorities.toArray(new String[] {}));
		} else 
			this.http.authorizeRequests().antMatchers(urlPattern).hasAuthority(authority);		
	}
}
