package com.app.vdc.demo.Controller;

import java.util.List;

import com.app.vdc.demo.dto.UsuarioLogado;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * O login e o refresh de token sao responsabilidade do Keycloak. A API apenas valida o
 * access token recebido e expoe os dados do usuario autenticado.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/me")
	public UsuarioLogado me(@AuthenticationPrincipal Jwt jwt, Authentication authentication) {
		List<String> roles = authentication.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.filter(authority -> authority.startsWith("ROLE_"))
			.toList();

		return new UsuarioLogado(jwt.getSubject(), jwt.getClaimAsString("preferred_username"),
				jwt.getClaimAsString("name"), jwt.getClaimAsString("email"), roles);
	}

}
