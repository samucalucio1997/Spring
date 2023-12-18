package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.AuthToken;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.ProdutoIS;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.ViaCep;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class ImplemController implements ViaCep{

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private UserService service;

     @Autowired
     private ProdutoService produto;

     private String Autentico; 
     private User Usuario;

     @PostMapping("/cadastroPro")
     public ResponseEntity<Boolean> PostCadastro(@RequestBody Produto pro) {
          boolean ret =  this.produto.CadastrarProduto(pro, this.Usuario);
          return ResponseEntity.ok(ret); 
     }    

     @GetMapping("/Categoria")
     public ArrayList<Categorias> GetCategoria() {
          ArrayList<Categorias> m = new ArrayList<>();
          return m;
     }

     @PostMapping("/cadastroUser")
     public ResponseEntity<AuthToken> PostCadastro(@RequestBody User usuario) {
          // service.CadastrarProduto(new Produto());
          User usuUser = this.service.CriarUser(usuario);
          usuUser.setPassword(TokenUtil.encodeToken(usuUser).getToken()); 
          return ResponseEntity.ok(new AuthToken(usuUser.getPassword()));
     }

     @GetMapping("/cep")
     public Endereco ConsultarCep(@PathVariable String name) {
          // TODO Auto-generated method stub
         return new Endereco();
     }

     @GetMapping("/login")
     public String Authentica(){
        return "Olá";
     }

     @PostMapping("/cadastraProduto")
     public ResponseEntity<Boolean> CadRegs(@RequestBody Produto produto){
          if(this.Autentico!=null){
               this.produto.CadastrarProduto(produto); 
               return ResponseEntity.ok(true);
          }else{
               return ResponseEntity.ok(false);
          }
     }




     //Testando o consumo da api externa
}
