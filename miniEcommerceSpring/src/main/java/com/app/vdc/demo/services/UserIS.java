package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;

import java.util.ArrayList;

public interface UserIS {
    public boolean AdicionarCarrinho(Produto produto,User cliente);
    public boolean EfetuarPedido();
    public boolean RemoverdoCarrinho(Long id);
    public boolean EditarCarrinho(Long id);
    public ArrayList<Produto> ListarItensCarrinho();
}
