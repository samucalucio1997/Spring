package com.app.vdc.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class GoogleUsuario {
    private String email;
    private String nome;
}
