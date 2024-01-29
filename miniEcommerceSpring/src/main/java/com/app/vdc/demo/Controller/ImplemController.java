package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.AuthToken;
import com.app.vdc.demo.Security.Login;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.ProdutoIS;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.ViaCep;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/home")
public class ImplemController {

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private UserService service;

     @Autowired
     private ProdutoService produto;

     private String Autentico; 

     @PostMapping("/cadastroPro")
     public ResponseEntity<Boolean> PostCadastro() {
          // boolean ret =  this.produto.CadastrarProduto(pro);
          return ResponseEntity.ok(true); 
     }    

     @GetMapping("/Categoria")
     public ArrayList<Categorias> GetCategoria() {
          ArrayList<Categorias> m = new ArrayList<>();
          return m;
     }
     
    
     @PostMapping(value = "/cadastroUser")
     public ResponseEntity<User> PostCadastro(
     @RequestParam("file") MultipartFile file,
     @RequestParam("username") String username,
     @RequestParam("password") String password,
     @RequestParam("first_name") String first_name,
     @RequestParam("last_name") String last_name,
     @RequestParam("email") String email,
     @RequestParam("cep") String cep,
     @RequestParam("numcasa") int numcasa,
     @RequestParam("is_active") boolean is_active
     ) throws IOException {
          // User usuario = new User(username, first_name, cep, email, password, numcasa, is_active);
          // User usuUser = this.service.CriarUser(usuario,file);
          System.out.println(file.getBytes());
          String msg = is_active?"está ativo":"ative imedia";
          System.out.println("Bem-Vindo " + username+", "+msg);
          return ResponseEntity.ok(new User());
     }

     @GetMapping("/cep")
     public String ConsultarCep(@RequestBody String name) {
          // TODO Auto-generated method stub
         return "Ola"+ name;
     }

     @PostMapping("/login")
     public ResponseEntity<User> Authentica(@RequestBody @Validated Login usuario){
      try {
           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
           new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
           org.springframework.security.core.Authentication auth = (org.springframework.security.core.Authentication) this.authenticationManager
           .authenticate(usernamePasswordAuthenticationToken);    
           var user =(User) auth.getPrincipal();     
           return ResponseEntity.ok(user);  
      } catch (UsernameNotFoundException e) {
          return ResponseEntity.badRequest().build();
      }    
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
