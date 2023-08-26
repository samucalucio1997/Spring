package com.app.vdc.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.vdc.demo.Model.Endereco;
/**
 * @param name
 * @return
 */

@FeignClient(name="viacep", url = "https://viacep.com.br/ws")
public interface ViaCep {
    
    @RequestMapping(method = RequestMethod.GET,value = "/{name}/json")
    Endereco ConsultarCep(@PathVariable String name);
}
