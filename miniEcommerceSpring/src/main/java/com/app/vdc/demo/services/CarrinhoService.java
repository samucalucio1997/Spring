package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarrinhoService implements CarrinhoIS {
    @Autowired
    public CarrinhoRepository carrinho;

    @Override
    public boolean adicionarItemcarrinho(User cliente, Produto produto) {
        Carrinho newItem=cliente.getCarrinho();
        List<Produto> novo=    newItem.getProdutos();
        if (cliente.isIs_staff()&&produto!=null) {
            novo.add(produto);
            carrinho.save(newItem);
            return true;
        }
        return false;
    }

        @Override
        public void atualizarQTD () {

        }
    }
