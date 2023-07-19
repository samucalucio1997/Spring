package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.services.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class ImplemController {
    @PostMapping("/casdastroPro")
    @ResponseBody
    public void PostCadastro(@RequestParam User admin,@RequestParam Produto produto){
        ProdutoService service = new ProdutoService();
        service.CadastrarProduto(produto,admin);
   }
   @GetMapping("/Categoria")
   public ArrayList<Categorias> GetCategoria(){
        ArrayList<Categorias> m = new ArrayList<>();
        return m;
   }
}
