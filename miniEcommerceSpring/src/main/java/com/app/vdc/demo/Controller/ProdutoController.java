package com.app.vdc.demo.Controller;

import java.io.IOException;
import java.util.List;

import com.app.vdc.demo.dto.ProdutoDTO;
import com.app.vdc.demo.services.Pagamento.PagamentoBoleto;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.vdc.demo.services.ProdutoIS;
import com.app.vdc.demo.dto.ProdutoResponse;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoIS produto;

    @Autowired
    private PagamentoBoleto pagamentoBoleto;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/cadastraProduto", consumes = "multipart/form-data")
    public ResponseEntity<Boolean> CadRegs(
          @RequestPart(value = "img", required = false)
          List<MultipartFile> file,
          @RequestPart(value = "produto") ProdutoDTO produto
          ) throws IOException{
          if(produto != null){
               this.produto.CadastrarProduto(produto, file != null ? file : null);
               return ResponseEntity.ok(true);
          }else{
               return ResponseEntity.ok(false);
          }
    }

    @GetMapping("/produtos")
    public Page<ProdutoDTO> ListarProdutos(
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "precoMin", required = false) Double precoMin,
            @RequestParam(value = "precoMax", required = false) Double precoMax,
            Pageable pageable
    ){
       return this.produto.ListarPro(categoria, precoMin, precoMax, pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/editarProduto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> editarftProduto(@RequestParam("produto_id") Integer produtoId, @RequestPart( value = "imagens", required = false) List<MultipartFile> imgs,
        @RequestParam("nome") String nome
    ){
         try {
              return ResponseEntity.status(200).body(this.produto.EditarProduto(produtoId, imgs, null));
         } catch (Exception e) {
              return ResponseEntity.status(401).body(new Message("deu errado", null, false)).badRequest().build();
         }
    }

    @GetMapping("/{id}")
    ResponseEntity<ProdutoResponse> produtoPorId(@PathVariable int id){
         return ResponseEntity.status(200).body(this.produto.PegarPorId(id));
    }
}
