package com.app.vdc.demo.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.vdc.demo.Model.Carrinho;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;

public class UserServiceTest {
    @InjectMocks
    private UserService service;
    
    @Mock
    private User cliente;
    
    @Mock
    private Produto produto;
    @Mock
    private Carrinho carrinho;

    @BeforeEach
    void start(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarCarrinho() {
        Startclient();
        assertTrue(service.AdicionarCarrinho(produto, cliente));         
    }

    void Startclient(){
        cliente = new User("asfdgasd",
         "zzdfna", "afdbnaf",
          "asedgbaqr", "zfbnafdsb"
          , "5486464-253", 1575, true);
    }

}
