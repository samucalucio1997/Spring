package com.app.vdc.demo.security;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.assertj.core.api.Assertions.assertThat;

class KeycloakJwtAuthenticationConverterTest {

	private final KeycloakJwtAuthenticationConverter converter = new KeycloakJwtAuthenticationConverter(
			"angular-frontend");

	@Test
	void deveConverterRolesDoRealmEDoClientEmAuthorities() {
		Jwt jwt = jwtBuilder().claim("realm_access", Map.of("roles", List.of("ADMIN", "user")))
			.claim("resource_access", Map.of("angular-frontend", Map.of("roles", List.of("gerente"))))
			.claim("scope", "profile email")
			.build();

		assertThat(authorities(jwt)).contains("ROLE_ADMIN", "ROLE_USER", "ROLE_GERENTE", "SCOPE_profile",
				"SCOPE_email");
	}

	@Test
	void deveIgnorarRolesDeOutrosClients() {
		Jwt jwt = jwtBuilder().claim("resource_access", Map.of("outro-app", Map.of("roles", List.of("financeiro"))))
			.build();

		assertThat(authorities(jwt)).doesNotContain("ROLE_FINANCEIRO");
	}

	@Test
	void deveUsarPreferredUsernameComoNomeDoPrincipal() {
		Jwt jwt = jwtBuilder().build();

		assertThat(this.converter.convert(jwt).getName()).isEqualTo("samuel");
	}

	@Test
	void naoDeveFalharQuandoTokenNaoTemRoles() {
		Jwt jwt = jwtBuilder().build();

		assertThat(authorities(jwt)).isEmpty();
	}

	private Jwt.Builder jwtBuilder() {
		return Jwt.withTokenValue("token").header("alg", "RS256").subject("f1c2").claim("preferred_username", "samuel");
	}

	private List<String> authorities(Jwt jwt) {
		return this.converter.convert(jwt).getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
	}

}
