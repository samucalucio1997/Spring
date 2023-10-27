package br.com.Pro.exerSB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.Pro.exerSB.Models.Produtos;
import br.com.Pro.exerSB.repository.ProdutoRepo;

@Component
public class ProdutoService implements ProdutoImplements {
    @Autowired
    private ProdutoRepo produto;

    @Override
    public boolean cadatrarProduto(Produtos pro) {
        produto.save(pro);
        return true;
    }

    @Override
    public List<Produtos> ListarProdutos() {
        return produto.findAll();
    }
}
