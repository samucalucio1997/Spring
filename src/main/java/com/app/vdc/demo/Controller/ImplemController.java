package com.app.vdc.demo.Controller;

import java.util.ArrayList;
import java.util.Optional;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class ImplemController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService service;

	@GetMapping("/Categoria")
	public ArrayList<Categorias> GetCategoria() {
		ArrayList<Categorias> m = new ArrayList<>();
		return m;
	}

	/**
	 * Cria o perfil local do usuario ja autenticado no Keycloak. Nome, e-mail e
	 * identificador vem do token, nunca do corpo da requisicao.
	 */
	@PostMapping("/perfil")
	public ResponseEntity<User> criarPerfil(@AuthenticationPrincipal Jwt jwt, @RequestParam("cep") String cep,
			@RequestParam("numcasa") int numcasa) {
		User usuario = new User(jwt.getSubject(), jwt.getClaimAsString("preferred_username"),
				jwt.getClaimAsString("given_name"), jwt.getClaimAsString("family_name"), jwt.getClaimAsString("email"),
				cep, numcasa, true);

		return ResponseEntity.ok(this.service.criarPerfil(usuario));
	}

	@GetMapping("/perfil")
	public ResponseEntity<User> meuPerfil(@AuthenticationPrincipal Jwt jwt) {
		return this.service.buscarPerfil(jwt.getSubject())
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<User> ConsultarCep(@PathVariable("id") int id) {
		return this.userRepository.findById(id);
	}

}
