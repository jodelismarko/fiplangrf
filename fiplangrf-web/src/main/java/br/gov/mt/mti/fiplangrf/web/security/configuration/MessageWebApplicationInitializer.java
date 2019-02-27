package br.gov.mt.mti.fiplangrf.web.security.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebSecurityConfig
 * <ul>
 * 	<li>Registra automaticamente o filtro springSecurityFilterChain para cada URL da aplicação</li>
 *  <li>Adiciona o ContextLoaderListener que carrega o SecurityConfig.
 * </ul>
 * @author 
 *
 */
public class MessageWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SecurityConfig.class, WebMvcConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}
