package com.app.vdc.demo.services.Pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import com.app.vdc.demo.Model.User;

@Service
public class PagamentoBoleto {

    @Value("${chave}")
    private String chave;







    


    public Object criarCliente(){
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Configurar os cabeçalhos
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("access-token", getChave());
            headers.set("User-Agent", "MyApp/1.0");

            // Criar o objeto de requisição
            Cliente bodyRequest = new Cliente("Lucas", "84991801842", "01746575441", "samucafab@gmail.com");
            HttpEntity<Cliente> requestEntity = new HttpEntity<>(bodyRequest, headers);

            // Fazer a requisição POST
            ResponseEntity<Object> response = restTemplate.exchange(
                    "https://sandbox.asaas.com/api/v3/customers",
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );

            // Retornar o corpo da resposta
            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    private class Cliente{
        @JsonProperty("name")
        private String name;
        @JsonProperty("cpfCnpj")
        private String cpfCnpj;
        @JsonProperty("mobilePhone")
        private String mobilePhone;
        @JsonProperty("email")
        private String email;

        public Cliente(String nome, String celular, String cpfCnpj,String email) {
            this.name = nome;
            this.mobilePhone = celular;
            this.cpfCnpj = cpfCnpj;
            this.email = email;
        }
    }

    private String getChave() {
        return chave;
    }
}
