package com.app.vdc.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * O Keycloak publica as roles em {@code realm_access.roles} e
 * {@code resource_access.<client-id>.roles}, claims que o Spring Security nao le por
 * padrao. Este conversor transforma as duas listas em authorities {@code ROLE_*} (em
 * maiusculo) para que {@code hasRole('ADMIN')} funcione, preservando os {@code SCOPE_*}
 * do conversor padrao.
 */
@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private static final String REALM_ACCESS = "realm_access";

	private static final String RESOURCE_ACCESS = "resource_access";

	private static final String ROLES = "roles";

	private static final String ROLE_PREFIX = "ROLE_";

	private final JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

	private final String clientId;

	public KeycloakJwtAuthenticationConverter(@Value("${keycloak.client-id:}") String clientId) {
		this.clientId = clientId;
	}

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		Collection<GrantedAuthority> authorities = Stream
			.concat(this.scopesConverter.convert(jwt).stream(), extractRoles(jwt).stream())
			.collect(Collectors.toSet());

		return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString("preferred_username"));
	}

	private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
		Stream<String> realmRoles = rolesOf(jwt.getClaimAsMap(REALM_ACCESS));
		Stream<String> clientRoles = rolesOf(clientAccess(jwt));

		return Stream.concat(realmRoles, clientRoles)
			.map(role -> ROLE_PREFIX + role.toUpperCase(Locale.ROOT))
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	private Map<String, Object> clientAccess(Jwt jwt) {
		Map<String, Object> resourceAccess = jwt.getClaimAsMap(RESOURCE_ACCESS);
		if (resourceAccess == null || this.clientId.isBlank()) {
			return Map.of();
		}
		return asMap(resourceAccess.get(this.clientId));
	}

	private Stream<String> rolesOf(Map<String, Object> access) {
		if (access == null || !(access.get(ROLES) instanceof List<?> roles)) {
			return Stream.empty();
		}
		return roles.stream().filter(String.class::isInstance).map(String.class::cast);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> asMap(Object value) {
		return (value instanceof Map) ? (Map<String, Object>) value : Map.of();
	}

}
