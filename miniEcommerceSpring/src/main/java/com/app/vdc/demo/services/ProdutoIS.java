package com.app.vdc.demo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.services.dto.ProdutoResponse;

public interface ProdutoIS {
 
    public boolean CadastrarProduto(Produto produto,List<MultipartFile> imgs) throws IOException;

    public boolean RemoverProduto(Produto produto);

    public boolean EditarProduto(Produto produto, int qtd);

    public List<ProdutoResponse> ListarPro();

    public Boolean EditarProduto(Produto produto, Categorias categoria);

    public boolean EditarProduto(Produto produto, float preco);

    public boolean EditarProduto(String produto, List<MultipartFile> imgs);
}
