package br.com.Pro.exerSB.service;

import java.util.List;

import br.com.Pro.exerSB.Models.Produtos;

public interface ProdutoImplements {
    public boolean cadatrarProduto(Produtos pr);
    public List<Produtos> ListarProdutos();
}
