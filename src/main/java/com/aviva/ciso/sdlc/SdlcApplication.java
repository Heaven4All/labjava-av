package com.aviva.ciso.sdlc;

import org.owasp.esapi.filters.SecurityWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SdlcApplication extends SpringBootServletInitializer {
	@Configuration
	public class BasicConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				// desactive le login
				.authorizeRequests().anyRequest().anonymous().and().httpBasic();
		}

		// permet d'utiliser h2-console
		// TODO: REMOVE
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("**");
		}
	}

	@Bean
	public FilterRegistrationBean<SecurityWrapper> securityFilter() {
		FilterRegistrationBean<SecurityWrapper> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SecurityWrapper());

		// TODO: Fix index.html not found because of /WEB-INF
		registrationBean.addInitParameter("allowableResourcesRoot", "/WEB-INF");
		// put filter on specific endpoint
		registrationBean.addUrlPatterns("test");
		return registrationBean;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SdlcApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SdlcApplication.class, args);
	}
}
