package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Config.FilestorageProperties;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.AuthToken;
import com.app.vdc.demo.Security.Login;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.UserloginReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


@RestController
@RequestMapping("/home")
public class ImplemController {

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private UserService service;

     @Autowired
     private ProdutoService produto;

     private final Path filestorageProperties;

     

     public ImplemController(FilestorageProperties filestorageProperties) {
          this.filestorageProperties = Paths.get(filestorageProperties.getUploadDir()).toAbsolutePath();
     }

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
          User usuario = 
          new User(username, first_name, last_name,
           email, password, cep, numcasa, is_active);
          User usuUser = this.service.CriarUser(usuario,file);
          System.out.println(file.getBytes());
          String msg = is_active?"está ativo":"ative imedia";
          System.out.println("Bem-Vindo " + username+", "+msg);
          return ResponseEntity.ok(usuUser);
     }

     @GetMapping("/cep")
     public String ConsultarCep(@RequestBody String name) {
          // TODO Auto-generated method stub
         return "Ola"+ name;
     }

     @PostMapping("/login")
     public ResponseEntity<UserloginReturn> Authentica(
     @RequestParam("username") String username,
     @RequestParam("password") String password
      ) throws IOException{
      
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
           new UsernamePasswordAuthenticationToken(username, password);
 
          Authentication auth = this.authenticationManager
           .authenticate(usernamePasswordAuthenticationToken);    

          var user =(User) auth.getPrincipal();
          Path rePath = this.filestorageProperties.resolve(user.getImagem()).toAbsolutePath();   
          UserloginReturn new_ret = new UserloginReturn(user, Files.readAllBytes(rePath));
          return ResponseEntity.ok(new_ret);
     }

     @PostMapping("/cadastraProduto")
     public ResponseEntity<Boolean> CadRegs(@RequestBody Produto produto){
          if(null==null){
               this.produto.CadastrarProduto(produto); 
               return ResponseEntity.ok(true);
          }else{
               return ResponseEntity.ok(false);
          }
     }




     //Testando o consumo da api externa
}
