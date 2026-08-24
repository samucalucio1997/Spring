package com.app.vdc.demo.dto;

import java.util.List;

public record UsuarioLogado(String id, String username, String nome, String email, List<String> roles) {
}
