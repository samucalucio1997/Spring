package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;

public interface ProdutoIS {
    public boolean CadastrarProduto(Produto produto, User cadastra);
    public boolean RemoverProduto(Produto produto);

    public void EditarProduto(Produto produto, int qtd);

    public void EditarProduto(Produto produto, Categorias categoria);

    public void EditarProduto(Produto produto, float preco);
}
