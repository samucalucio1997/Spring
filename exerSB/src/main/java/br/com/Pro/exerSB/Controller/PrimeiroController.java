package br.com.Pro.exerSB.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {
	@GetMapping(path = "/s")
	public String ola() {
		return "Olá, Spring Bring";
	}
}
