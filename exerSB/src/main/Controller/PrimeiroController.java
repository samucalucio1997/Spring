package br.com.Pro.exerSB.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {
    @RequestMapping(method = RequestMethod.GET)
	public String ola() {
	   return "Olá Spring Bring";
   }
}
