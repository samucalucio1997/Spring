package com.app.vdc.demo.services;

import com.app.vdc.demo.Config.FileStorageProduct;
import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.ImagemProduto;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.ImagemProdutoRepository;
import com.app.vdc.demo.repository.ProdutoRepository;
import com.app.vdc.demo.services.dto.ProdutoResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProdutoService implements ProdutoIS{
    
    @Autowired
    private ProdutoRepository produtos;

    @Autowired
    private ImagemProdutoRepository imgProduto;

    private final Path caminho;

    

    public ProdutoService(FileStorageProduct fileStorageProduct) {
        this.caminho = Paths.get(fileStorageProduct.getSobeImg()).toAbsolutePath().normalize();
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
          if(produto!=null && qtd>0){
            Produto pro =  produtos.getById(produto.getId());
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

    public ProdutoResponse PegarPorId(int id){
        if (this.produtos.findById(id).isPresent()) {
            Produto produto = this.produtos.getById(id);
            List<byte[]> imgs =new ArrayList<>();
            produto.getImagens().stream().forEach( img -> {
                Path caminho = this.caminho.resolve(img.getPath()).toAbsolutePath().normalize();
                try {
                    imgs.add(Files.readAllBytes(caminho));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }   
            });
            return new ProdutoResponse(imgs, produto);
        }else{
            return new ProdutoResponse(null, null);
        }  
    }

    @Override
    public boolean CadastrarProduto(Produto produto,List<MultipartFile> imgs) throws IOException{
        try {
            Produto pro = produtos.findAll().stream().filter(n -> n.getNome().equals(produto.getNome())
            &&n.getCategoria().equals(produto.getCategoria())).findAny().get();
            if (pro!=null) {
               throw new RuntimeException("Já existe esse Produto");
            }
            if (!imgs.isEmpty()) {
               imgs.stream().forEach(n->{
                   try {
                    String nome =  n.getOriginalFilename();
                    Path path = this.caminho.resolve(nome).toAbsolutePath().normalize();
                    n.transferTo(path);
                    ImagemProduto imgp = new ImagemProduto();
                    imgp.setPath(nome);
                    this.imgProduto.save(imgp);
                    produto.getImagens().add(imgp);
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

               });
            }
            
            return this.produtos.save(produto)!=null;
        } catch (Exception e) {
           throw e;
        }
    }

    @Override
    public List<ProdutoResponse> ListarPro() {
        // TODO Auto-generated method stub
        List<ProdutoResponse> lista = new ArrayList<>();
        this.produtos.findAll().stream()
        .forEach(n -> {
            List<byte[]> byteimg = new ArrayList<>();
            if (n.getImagens()!=null) {
                n.getImagens().stream()
                .forEach(p -> {
                  Path path = this.caminho.resolve(p.getPath()).toAbsolutePath();
                  try {
                      byteimg.add(Files.readAllBytes(path));
                    } catch (Exception e) {
                    // TODO: handle exception
                    e.getStackTrace();
                } 
                
            });
            }
        ProdutoResponse resp = new ProdutoResponse(byteimg, n);
        lista.add(resp);
        });


        return lista;
    }



    @Override
    public boolean EditarProduto(String nome_produto, List<MultipartFile> imgs, Produto produto) {
        // TODO Auto-generated method stub
        Produto produtoAtual = this.produtos
        .findByNome(nome_produto);
        if (!imgs.isEmpty()) {
            imgs.stream().forEach(
                n -> {
                String cami = n.getOriginalFilename();
                Path path = this.caminho.resolve(cami).toAbsolutePath().normalize();
               try {
                n.transferTo(path);
                ImagemProduto pImpr = new ImagemProduto();
                pImpr.setPath(cami);
                this.imgProduto.save(pImpr);
                produtoAtual.getImagens().add(pImpr);
                this.produtos.save(produtoAtual);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            });            
        }
        //produtoAtual.getImagens().
        return false;
    }
}
