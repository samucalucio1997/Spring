package com.app.vdc.demo.services.Pagamento;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.vdc.demo.Model.User;

@Service
public class PagamentoBoleto {
   
    
    public void GerarBoleto(User usuario){
        WebClient.create("https://sandbox.asaas.com/api/v3/payments")
        .post()
        .body(usuario, getClass())
        .header("content-type", "application/json")
        .retrieve()
        .bodyToFlux(Object.class);
        
    }


    private class ReqBodyPay{
        private String id;
        private String BillingType;
        private float value;
        private Date dueDate;  
        private String description;
    }
}
