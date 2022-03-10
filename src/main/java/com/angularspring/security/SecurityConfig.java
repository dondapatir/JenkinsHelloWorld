package com.angularspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
				.and().httpBasic();*/
		 http
         .authorizeRequests()
         .anyRequest()
         .authenticated()
         .and()
         .httpBasic();
	}

	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	PasswordEncoder encoder = 
	          PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    	auth
	          .inMemoryAuthentication()
	          .withUser("user")
	          .password(encoder.encode("password"))
	          .roles("USER")
	          .and()
	          .withUser("admin")
	          .password(encoder.encode("admin"))
	          .roles("USER", "ADMIN");
	    }
}