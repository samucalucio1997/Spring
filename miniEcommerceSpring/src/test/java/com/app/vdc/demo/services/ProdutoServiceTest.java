package com.app.vdc.demo.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Pedido;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;

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
             
    }
    private void startProduto(){
         produto = new Produto(1,45,user,
         420.3f,Categorias.eletronicos);
        //  optionalProduto= Optional.of(new Produto(1,
        //  45,user,420.3f,Categorias.eletronicos));  
    }
    private void startConsumer(){
        user= new User(1L,"samuca","Samuel","Farias"
        ,"samucalucio@hotmail.com"
        ,"#123",carrinho,"59064-290",
        1547,true,pedido);
    }
    private void startCarrinho(){
        listprodutos.add(1, produto); 
        carrinho = new Carrinho(1L,listprodutos,user);
    }
    private void startPedido(){
        pedido = new Pedido(1L,LocalTime.now(),
        user,carrinho,true);
    }
}
