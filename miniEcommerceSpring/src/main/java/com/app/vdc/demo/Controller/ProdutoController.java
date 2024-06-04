package com.app.vdc.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.services.ProdutoIS;
import com.app.vdc.demo.services.dto.ProdutoResponse;

import antlr.debug.MessageAdapter;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @Autowired
    private ProdutoIS produto;



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/cadastraProduto",consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Boolean> CadRegs(
          @RequestParam(value = "img", required = false) 
          List<MultipartFile> file,
          @RequestBody Produto produto
          ) throws IOException{
          if(null==null){
               this.produto.CadastrarProduto(produto,file!=null?file:null); 
               return ResponseEntity.ok(true);
          }else{
               return ResponseEntity.ok(false);
          }
     }
     @GetMapping("/produtos")
     public ResponseEntity<List<ProdutoResponse>> ListarProdutos(){
        return  ResponseEntity.status(200).body(this.produto.ListarPro());
     }

     @PreAuthorize("hasRole('ROLE_ADMIN')")
     @PatchMapping("/adimgs")
     public ResponseEntity<Boolean> editarftProduto(@RequestParam("nome") String nomePro,@RequestParam("imagens") List<MultipartFile> imgs){
          try {         
               return ResponseEntity.status(200).body(this.produto.EditarProduto(nomePro, imgs));
          } catch (Exception e) {
               // TODO: handle exception
               return ResponseEntity.status(401).body(new Message("deu errado", null, false)).badRequest().build();
          }
     }

     
     @GetMapping("/{id}")
     ResponseEntity<ProdutoResponse> produtoPorId(@PathVariable int id){          
          return ResponseEntity.status(200).body(this.produto.PegarPorId(id));
     }
}
