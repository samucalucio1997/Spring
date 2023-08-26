package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.ViaCep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class ImplemController implements ViaCep{

     @Autowired
     private UserService service;

     

     @PostMapping("/casdastroPro")
     @ResponseBody
     public void PostCadastro(@RequestParam User admin, @RequestParam Produto produto) {
          ProdutoService service = new ProdutoService();
          service.CadastrarProduto(produto, admin);
     }

     @GetMapping("/Categoria")
     public ArrayList<Categorias> GetCategoria() {
          ArrayList<Categorias> m = new ArrayList<>();
          return m;
     }

     @PostMapping("/cadastroUser")
     public String PostCadastro() {
          ProdutoService service = new ProdutoService();
          // service.CadastrarProduto(new Produto());
          return "Authorized service";
     }

     @Override
     @GetMapping("/cep")
     public Endereco ConsultarCep(@PathVariable String name) {
          // TODO Auto-generated method stub
        
          throw new UnsupportedOperationException("Unimplemented method 'ConsultarCep'");
     }


     //Testando o consumo da api externa
    

}
