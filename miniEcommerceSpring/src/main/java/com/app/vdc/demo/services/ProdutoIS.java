package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;

public interface ProdutoIS {
    public boolean CadastrarProduto(Produto produto, User cadastra);
 
    public boolean CadastrarProduto(Produto produto);

    public boolean RemoverProduto(Produto produto);

    public boolean EditarProduto(Produto produto, int qtd);

    public Boolean EditarProduto(Produto produto, Categorias categoria);

    public boolean EditarProduto(Produto produto, float preco);
}
