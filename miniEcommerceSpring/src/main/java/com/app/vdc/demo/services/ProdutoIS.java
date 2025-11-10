package com.app.vdc.demo.services;

import java.io.IOException;
import java.util.List;

import com.app.vdc.demo.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.dto.ProdutoResponse;

public interface ProdutoIS {
 
    public boolean CadastrarProduto(Produto produto,List<MultipartFile> imgs) throws IOException;

    public boolean RemoverProduto(Produto produto);

    public boolean EditarProduto(Produto produto, int qtd);

    public Page<ProdutoDTO> ListarPro(String categoria,
                                      Double precoMin,
                                      Double precoMax,
                                      Pageable pageable);
    
    public ProdutoResponse PegarPorId(int id);

    public Boolean EditarProduto(Produto produto, Categorias categoria);

    public boolean EditarProduto(Produto produto, float preco);

    public boolean EditarProduto(String nome_produto, List<MultipartFile> imgs, Produto produto);
}
