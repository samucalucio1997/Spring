package com.app.vdc.demo.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteAsaas {
    @JsonProperty("name")
    private String name;
    @JsonProperty("cpfCnpj")
    private String cpfCnpj;
    @JsonProperty("mobilePhone")
    private String mobilePhone;
    @JsonProperty("email")
    private String email;

    public ClienteAsaas(String name, String cpfCnpj, String mobilePhone, String email) {
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }
}
