package com.app.vdc.demo.services.Pagamento;

import com.app.vdc.demo.dto.ClienteAsaas;
import com.app.vdc.demo.dto.UsuarioSalvo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PagamentoBoleto {

    @Value("${chave}")
    private String chave;


    public UsuarioSalvo pagarBoleto(){
        return null;
    }




    







    public ClienteAsaas criarCliente(UsuarioSalvo usuario){
        try {
            RestTemplate restTemplate = new RestTemplate();


            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("access-token", getChave());
            headers.set("User-Agent", "MyApp/1.0");


            ClienteAsaas bodyRequest = new ClienteAsaas(usuario.getUsername(), usuario.getCpfCnpj(), usuario.getTelefone(), usuario.getEmail());
            HttpEntity<ClienteAsaas> requestEntity = new HttpEntity<>(bodyRequest, headers);


            ResponseEntity<ClienteAsaas> response = restTemplate.exchange(
                    "https://sandbox.asaas.com/api/v3/customers",
                    HttpMethod.POST,
                    requestEntity,
                    ClienteAsaas.class
            );


            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getChave() {
        return chave;
    }
}
