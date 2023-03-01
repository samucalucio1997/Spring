package br.com.Pro.exerSB.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Pro.exerSB.Models.Cliente;

@RestController
@RequestMapping("/Consulta")
public class ClienteController {
	@GetMapping(path = "/Clientes")
     public Cliente ObterCliente() {
    	 return new Cliente(28,"Carlos","004789519-62");
     }
}
