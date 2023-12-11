package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.AuthToken;
import com.app.vdc.demo.Security.MyFilter;
import com.app.vdc.demo.Security.TokenUtil;
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

     @GetMapping("/cadastroUser")
     public String PostCadastro() {
          // service.CadastrarProduto(new Produto());
          return "Authorized service";
     }

     @GetMapping("/cep")
     public Endereco ConsultarCep(@PathVariable String name) {
          // TODO Auto-generated method stub
         return new Endereco();
     }

     @GetMapping("/login")
     public ResponseEntity<AuthToken> Authentica(@RequestBody User usuario){
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
       new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
       Authentication authenticate = 
       (Authentication) this.authenticationManager
       .authenticate(usernamePasswordAuthenticationToken);
       authenticate.getRealm();
       User usuUser=new User();
       usuUser.setUsername(authenticate.getUsername());
       usuUser.setPassword(authenticate.getPassword());  
       return ResponseEntity.ok(TokenUtil.encodeToken(usuUser));
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
