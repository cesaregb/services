package com.il.sod.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
/**
 * This code was obtained from: http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
 * but it fits what we want to accomplish
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM="MY_TEST_REALM";

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		final String user = (System.getenv("SEC_USER") == null)?"user":System.getenv("SEC_USER");
		final String password = (System.getenv("SEC_PASSWORD") == null)?"thousandeyes":System.getenv("SEC_PASSWORD");

		auth.inMemoryAuthentication().withUser(user).password(password).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// we will require admin for all of it,
		// maybe if we had a Bearer authenticaton we could have allowed some endpoints for authentication,
		// then we can return the token from there.
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/**").hasRole("ADMIN")
				.and().httpBasic().realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint())
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}

	/* To allow Pre-flight [OPTIONS] request from browser */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
	
}