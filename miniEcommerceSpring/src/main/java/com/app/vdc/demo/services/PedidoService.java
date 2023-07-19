package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoService implements  PedidoIS{
    @Autowired
    public CarrinhoRepository carrinho;

    @Override
    public String FinalizarPedido(Carrinho car) {
        float Total=0.0F;
        if(!car.getProdutos().isEmpty()){//pegar o id do carrinho "car" no banco de dados use a dependencia
            // carrinho do Carrinho Repository
              List<Produto> Tot = car.getProdutos();
              for(Produto Subt:Tot){
                  Total=+(Subt.getPrecoUni()* Subt.getQtd());
              }
           carrinho.save(car);
        }
        return "Pedido do Cliente " + car.getConsumidor() + " foi finalizada com sucesso." +
                " Total da compra: R$"+Total;
    }
}
