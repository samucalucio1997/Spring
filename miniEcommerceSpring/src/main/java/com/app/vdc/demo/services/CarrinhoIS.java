package com.app.vdc.demo.services;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
public interface CarrinhoIS {
    public boolean adicionarItemcarrinho(User cliente, Produto produto);

    public void atualizarQTD();
}
