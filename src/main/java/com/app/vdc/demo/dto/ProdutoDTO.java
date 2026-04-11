package com.app.vdc.demo.dto;

import java.util.List;
import com.app.vdc.demo.Model.Categorias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private int id;
    private String nome;
    private int qtd;
    private float precoUni;
    private String descricao;
    private Categorias categoria;
    private List<ImagemProdutoDTO> imagens;
}

