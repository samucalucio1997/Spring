package com.app.vdc.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

	private final KeycloakJwtAuthenticationConverter jwtAuthenticationConverter;

	public SecurityConfigurations(KeycloakJwtAuthenticationConverter jwtAuthenticationConverter) {
		this.jwtAuthenticationConverter = jwtAuthenticationConverter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors()
			.and()
			// API stateless autenticada por Bearer token: nao ha sessao/cookie a proteger
			.csrf()
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.GET, "/produto/produtos", "/produto/*")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/files/*")
				.permitAll()
				.antMatchers("/actuator/health", "/actuator/health/**", "/actuator/info")
				.permitAll()
				.anyRequest()
				.authenticated())
			.oauth2ResourceServer(
					oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(this.jwtAuthenticationConverter)))
			.build();
	}

}
