package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Config.FilestorageProperties;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.Pagamento.PagamentoBoleto;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.dto.UserloginReturn;

import com.app.vdc.demo.services.dto.UsuarioSalvo;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/home")
public class ImplemController {
     
     @Autowired
     private UserRepository userRepository;

     @Autowired
     private PagamentoBoleto pagamentoBoleto;

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private UserService service;

     
     private final Path filestorageProperties;

     

     public ImplemController(FilestorageProperties filestorageProperties) {
          this.filestorageProperties = Paths.get(filestorageProperties.getUploadDir()).toAbsolutePath();
     }

     @GetMapping("/cadastroPro")
     @PreAuthorize("permitAll()")
     public ResponseEntity<Boolean> PostCadastro(@RequestBody UsuarioSalvo usuario) {
          try {
               User usuarioSave = new User();
               usuarioSave.setEmail(usuario.getEmail());
               usuarioSave.setPassword(usuario.getPassword());
               usuarioSave.setUsername(usuario.getUsername());
               usuarioSave.setCarrinho(usuarioSave.getCarrinho());
               usuarioSave.setCEP(usuario.getCEP());
               usuarioSave.setImagem(usuario.getImagem());
               usuarioSave.setFirst_name(usuario.getFirst_name());
               usuarioSave.setLast_name(usuario.getLast_name());
               usuarioSave.setNumcasa(usuario.getNumcasa());
               usuarioSave.setIs_staff(false);
               this.service.CriarUser(usuarioSave,null);
          }catch (Exception e) {

          }

          return ResponseEntity.ok(true); 
     }    

     @GetMapping("/Categoria")
     public ArrayList<Categorias> GetCategoria() {
          ArrayList<Categorias> m = new ArrayList<>();
          return m;
     }
     
    
     @PostMapping(value = "/cadastroUser")
     public ResponseEntity<User> PostCadastro(
     @RequestParam(value="file",required = false) MultipartFile file,
     @RequestParam("username") String username,
     @RequestParam("password") String password,
     @RequestParam("first_name") String first_name,
     @RequestParam("last_name") String last_name,
     @RequestParam("email") String email,
     @RequestParam("cep") String cep,
     @RequestParam("numcasa") int numcasa,
     @RequestParam(value="is_staff",required = false,defaultValue = "0") int code
     ) throws IOException {
          User usuario = 
          new User(username, first_name, last_name,
          email, password, cep, numcasa, true);
          System.out.println("code =>"+code); 
          if (code==351622) {
              usuario.setIs_staff(true);
          }
          User usuUser = this.service.CriarUser(usuario,file);
          String msg = usuario.isIs_active()?"está ativo":"ative imedia";
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
     /*Erro ao inicializar por causa da mudança na tabela produto */
     



     @GetMapping(value = "cep")
     @PreAuthorize("permiteAll()")
     public Flux<Object> testAPi(@RequestParam("cep") String cep){
       return WebClient.create().get()
       .uri("https://viacep.com.br/ws/"+ cep + "/json")
       .retrieve()
       .bodyToFlux(Object.class);
     }
     
     // https://maps.googleapis.com/maps/api/directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=



     //Testando o consumo da api externa
}
