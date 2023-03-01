package br.com.Pro.exerSB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Pro.exerSB.Models.ProdutoRepository;
import br.com.Pro.exerSB.Models.Produtos;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	 @Autowired
	 private ProdutoRepository produto;
	 @PostMapping
     public @ResponseBody Produtos postPro(@RequestParam String nome) {//
    	 Produtos pro = new Produtos(nome);
    	 produto.save(pro);
    	 return pro;
     }
}
