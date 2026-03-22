package com.app.vdc.demo.dto;

import com.app.vdc.demo.Model.User;

public class UsuarioSalvo extends User {
    private String cpfCnpj;
    private String telefone;

    public UsuarioSalvo(String username, String first_name, String last_name, String email, String password, String cEP, int numcasa, boolean is_active) {
        super(username, first_name, last_name, email, password, cEP, numcasa, is_active);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }


}
