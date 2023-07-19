package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoService implements ProdutoIS{
    @Autowired
    public ProdutoRepository produtos;

    @Override
    public boolean CadastrarProduto(Produto produto, User cadastra) {
        if(cadastra.isIs_staff()){
                produtos.save(produto);
                return true;
        }
        return false;
    }

    @Override
    public boolean RemoverProduto(Produto produto) {
        if(produto.getId()>0){
            produtos.deleteById(produto.getId());
            return true;
        }
        return false;
    }

    @Override
    public void EditarProduto(Produto produto,int qtd) {
          if(produto!=null&&qtd>0){
            Produto pro=  produtos.getById(produto.getId());
            pro.setQtd(qtd);
          }
    }
    @Override
    public void EditarProduto(Produto produto, float preco){
        if(produto!=null&&preco>0.0){
            produtos.getById(produto.getId()).setPrecoUni(preco);
        }
    }
    @Override
    public void EditarProduto(Produto produto, Categorias categoria){
        if(produto!=null&&(categoria==Categorias.roupas
                ||categoria==Categorias.calçados||categoria==Categorias.eletronicos
                ||categoria==Categorias.esportes)){
               produtos.getById(produto.getId()).setCategoria(categoria);
        }
    }
}
