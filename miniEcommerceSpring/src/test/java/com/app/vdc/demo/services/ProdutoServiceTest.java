package com.app.vdc.demo.services;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Pedido;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Model.Categorias;

public class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private Produto produto;

    @Mock
    private User user;
    @Mock
    private Pedido pedido;
    @Mock
    private Carrinho carrinho;
    List<Produto> listprodutos = new ArrayList<>();
    @BeforeEach
    void setup(){
     MockitoAnnotations.openMocks(this);
    }  

    @Test
    void testCadastrarProduto() {
        setup();
        startConsumer();
        startProduto();
        Assertions.assertTrue(produtoService.
        CadastrarProduto(produto,user));

    }

    private void startProduto(){
         produto = new Produto(45,420.3f,Categorias.eletronicos);
        //  optionalProduto= Optional.of(new Produto(1,45,user,420.3f,Categorias.eletronicos));  
    }
    private void startConsumer(){
        user= new User("samuca","Samuel","Farias"
        ,"samucalucio@hotmail.com"
        ,"#123","59064-290",
        1547,true);
    }
    private void startCarrinho(){
        listprodutos.add(1, produto); 
        // carrinho = new Carrinho();
    }
    private void startPedido(){
        pedido = new Pedido();
    }
}
