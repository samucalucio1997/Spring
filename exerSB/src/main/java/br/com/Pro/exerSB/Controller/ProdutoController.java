package br.com.Pro.exerSB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Pro.exerSB.Models.Produtos;
import br.com.Pro.exerSB.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
    private ProdutoService produtoService; 

	 @PostMapping("/cadastro")
	 @ResponseBody
     public String postPro(@RequestParam String nome) {//    	 
		 produtoService.cadatrarProduto(nome);
    	 return nome;
     }
}
