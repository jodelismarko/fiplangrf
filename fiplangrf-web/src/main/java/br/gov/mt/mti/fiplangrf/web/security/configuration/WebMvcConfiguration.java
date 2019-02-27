package br.gov.mt.mti.fiplangrf.web.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.gov.mt.mti.fiplangrf.common.util.Constantes;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Constantes.BASE_PACKAGE + ".web.security")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override 
    public void addViewControllers(ViewControllerRegistry registry) { 
    	
        registry.addViewController("/login") //Requisição para login
                .setViewName("login");//Nome da página (é acrescentado automaticamente o sufixo .jsp)         
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE); 
    } 
    
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}