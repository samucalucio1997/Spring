package com.app.vdc.demo.dto;

import lombok.Data;

@Data
public class ImagemProdutoDTO {
    private int id;
    private String nomeArquivo;
    private String caminhoArquivo;
    private String tipoArquivo;
    private long tamanhoArquivo;

}

