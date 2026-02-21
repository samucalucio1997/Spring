package com.app.vdc.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UsuarioDto {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String email;

    private String role;

    public String getRole() {
        if (this.role != null && !this.role.isEmpty()) {
            return this.role;
        }
        return "ROLE_USER";
    }

    public void setRole(String role) {
        final var isValidCurrentRole = this.role == null || this.role.isEmpty();
        final var isValidNewRole = !role.isEmpty() && role.startsWith("ROLE_");

        if (isValidCurrentRole && isValidNewRole) {
            this.role = role;
        } else {
            this.role = null;
        }
    }
}
