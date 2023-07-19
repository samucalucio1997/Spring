package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Produto;

import java.util.ArrayList;

public interface UserIS {
    public boolean AdicionarCarrinho(Produto produto);
    public void EfetuarPedido();
    public boolean RemoverdoCarrinho(Long id);
    public void EditarCarrinho(Long id);
    public ArrayList<Produto> ListarItensCarrinho();
}
