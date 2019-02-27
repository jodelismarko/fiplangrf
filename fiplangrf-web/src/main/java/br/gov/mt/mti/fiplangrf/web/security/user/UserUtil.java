package br.gov.mt.mti.fiplangrf.web.security.user;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public final class UserUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3587641344399967471L;

	@Produces
	public static UserDetail getUserDetail() {
		UserDetail user = null;
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return user;
	}

	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
