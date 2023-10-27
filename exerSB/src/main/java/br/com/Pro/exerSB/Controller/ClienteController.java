package br.com.Pro.exerSB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Pro.exerSB.Models.Cliente;
import br.com.Pro.exerSB.Models.Produtos;
import br.com.Pro.exerSB.service.ProdutoService;

@RestController
@RequestMapping("/consulta")
public class ClienteController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/clientes")
    public Cliente ObterCliente() {
        return null;
        // produtoService.
    }

    @GetMapping("/listar")
    public List<Produtos> getLista() {
        return produtoService.ListarProdutos();
    }
}
