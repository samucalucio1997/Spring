package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Endereco;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.AuthToken;
import com.app.vdc.demo.Security.MyFilter;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.services.ProdutoService;
import com.app.vdc.demo.services.UserService;
import com.app.vdc.demo.services.ViaCep;

import org.hibernate.result.NoMoreReturnsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class ImplemController implements ViaCep{

     @Autowired
     private UserService service;

     @Autowired
     private ProdutoService produto;

     private User Autentico; 
     

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

     @GetMapping("/cadastroUser")
     public String PostCadastro() {
          ProdutoService service = new ProdutoService();
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
         if(usuario.getUsername().equals("samuca")&&usuario.getPassword().equals("ticao")){
           this.Autentico = usuario;
           return ResponseEntity.ok(TokenUtil.encodeToken(usuario));
         }else{
            return ResponseEntity.status(403).build();
         }
     }

     @PostMapping("/cadastraProduto")
     public ResponseEntity<Boolean> CadRegs(Produto produto){
          try {
               if(this.Autentico!=null&&this.Autentico.isIs_staff()){
                    this.produto.CadastrarProduto(produto); 
                    return ResponseEntity.ok(true);
               }else{
                   return ResponseEntity.ok(false);
               }
          } catch (Exception e) {
             ResponseEntity.status(401);
             throw new NoMoreReturnsException("não se pode operar");
          }
     }




     //Testando o consumo da api externa
}
