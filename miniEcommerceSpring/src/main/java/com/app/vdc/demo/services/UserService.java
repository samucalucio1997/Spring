package com.app.vdc.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.UserRepository;

public class UserService implements UserIS {

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private UserRepository Consumer;

    @Override
    public boolean AdicionarCarrinho(Produto produto, User cliente) {
        if (cliente.getCarrinho() != null && Consumer.findById(cliente.getId()) != null) {
            cliente.getCarrinho().getProdutos().add(produto);
            System.out.println(Consumer.save(cliente));
            return true;
        } else {
            if(carrinho!=null){
                cliente.setCarrinho(carrinho);
                System.out.println(cliente.getCarrinho().getProdutos().add(produto));
                return true;
            }
            return false;
        }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'AdicionarCarrinho'");
    }

    @Override
    public boolean EfetuarPedido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EfetuarPedido'");
    }

    @Override
    public boolean RemoverdoCarrinho(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RemoverdoCarrinho'");
    }

    @Override
    public boolean EditarCarrinho(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EditarCarrinho'");
    }

    @Override
    public ArrayList<Produto> ListarItensCarrinho() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarItensCarrinho'");
    }

}
