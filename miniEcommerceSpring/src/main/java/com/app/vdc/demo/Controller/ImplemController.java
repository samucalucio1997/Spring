package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Config.FilestorageProperties;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.UserloginReturn;

import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/home")
public class ImplemController {
     
     @Autowired
     private UserRepository userRepository;

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

     @GetMapping("/{id}")
     public Optional<User> ConsultarCep(@PathVariable("id") int id) {
          // TODO Auto-generated method stub
         return this.userRepository.findById(id);
     }

     @PostMapping("/login")
     public ResponseEntity<UserloginReturn> Authentica(
     @RequestParam("username") String username,
     @RequestParam("password") String password,
     HttpSession httpSession
      ) throws IOException{
      
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
           new UsernamePasswordAuthenticationToken(username, password);
 
          Authentication auth = this.authenticationManager
           .authenticate(usernamePasswordAuthenticationToken);    

          var user =(User) auth.getPrincipal();
          httpSession.setAttribute("user", user);
          
          return ResponseEntity.ok(new UserloginReturn(user, TokenUtil.encodeToken(user)));
     }
     
     @PostMapping("/image")
     public ResponseEntity<byte[]> GetImage(@RequestParam("id") int id) throws IOException{
          User pessoa = this.userRepository.findById(id).get();
          Path path = this.filestorageProperties.resolve(pessoa.getImagem()).toAbsolutePath();
          return ResponseEntity.ok(Files.readAllBytes(path));
     }

     @GetMapping("/logout")
     public void logout(HttpSession httpSession){
          System.out.println("deslogado");
          httpSession.invalidate();
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
     
     @GetMapping(value = "cep")
     public Flux<Object> testAPi(@RequestParam("cep") String cep){
       return WebClient.create().get()
       .uri("https://viacep.com.br/ws/"+ cep + "/json")
       .retrieve()
       .bodyToFlux(Object.class);
     }
     
     // https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=



     //Testando o consumo da api externa
}
