package com.app.vdc.demo.services;

import com.app.vdc.demo.Config.FileStorageProduct;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.ProdutoRepository;

import java.util.List;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoService implements ProdutoIS{
    
    @Autowired
    public ProdutoRepository produtos;

    @Autowired
    private FileStorageProduct fileStorageProduct;

    @Override
    public boolean CadastrarProduto(Produto produto, User cadastra) {
        if(!cadastra.isIs_staff()){
                produtos.save(produto); 
                return true;
        }
        return false;
    }

    @Override
    public boolean RemoverProduto(Produto produto) {
        if(produto.getId() > 0){
            produtos.deleteById(produto.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean EditarProduto(Produto produto,int qtd) {
          if(produto!=null&&qtd>0){
            Produto pro=  produtos.getById(produto.getId());
            pro.setQtd(qtd);
            return true;
          }else{
            return false;
          }
    }
    @Override
    public boolean EditarProduto(Produto produto, float preco){
        if(produto!=null&&preco>0.0){
            produtos.getById(produto.getId()).setPrecoUni(preco);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Boolean EditarProduto(Produto produto, Categorias categoria){
        if(produto!=null&&categoria !=null){
               produtos.getById(produto.getId()).setCategoria(categoria); 
               return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean CadastrarProduto(Produto produto) {
        try {
            Produto pro = produtos.findAll().stream().filter(n -> n.getNome().equals(produto.getNome())
            &&n.getCategoria().equals(produto.getCategoria())).findAny().get();
            if (pro!=null) {
               throw new RuntimeException("Já existe esse Produto");
            }
            
            return this.produtos.save(produto)!=null;
        } catch (Exception e) {
           throw new RuntimeCryptoException(null);
        }
    }

    @Override
    public List<Produto> ListarPro() {
        // TODO Auto-generated method stub
        return null;
    }
}
