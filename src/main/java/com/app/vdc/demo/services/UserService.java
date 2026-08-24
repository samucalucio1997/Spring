package com.app.vdc.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User criarPerfil(User usuario) {
		if (this.userRepository.existsByKeycloakId(usuario.getKeycloakId())) {
			throw new IllegalStateException("Perfil ja cadastrado para este usuario");
		}
		return this.userRepository.save(usuario);
	}

	public Optional<User> buscarPerfil(String keycloakId) {
		return this.userRepository.findByKeycloakId(keycloakId);
	}

}
