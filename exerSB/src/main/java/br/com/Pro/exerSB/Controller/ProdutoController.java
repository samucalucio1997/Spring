package br.com.Pro.exerSB.Controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.Pro.exerSB.Models.Produtos;
import br.com.Pro.exerSB.repository.ProdutoRepo;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
    private ProdutoRepo produtoService; 

	 @PostMapping(value = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Produtos> postPro(@RequestBody Produtos pr) {
		 var produto = produtoService.save(pr);
		 URI Location = ServletUriComponentsBuilder
		 .fromCurrentRequest()
		 .path("/{id}")
		 .buildAndExpand(produto.getId()).toUri();

    	 return ResponseEntity.created(Location).body(produto);
     }
     
	 @GetMapping("/buscar")
	 public ResponseEntity<Produtos> PegarPro(@RequestParam String nome){
         Optional<Produtos> prod = produtoService.findAll()
		 .stream().filter(n -> {
			String name = n.getNome();
			return nome != null && nome.equals(name);
		}).findFirst();
		 if (prod.isPresent()) {
			return ResponseEntity.ok(prod.get());
		} else {
			// Retornar uma resposta adequada quando o produto não for encontrado.
			return ResponseEntity.notFound().build();
		}
		}
     @GetMapping("/NewFile") 
	 @ResponseBody
	 public ModelAndView rendeRView(){
          ModelAndView new_view = new ModelAndView();
		  new_view.setViewName("NewFile.html");
		  List<Produtos> pro = produtoService.findAll();
		  new_view.addObject(pro);
		  return new_view;
	 }	

}
