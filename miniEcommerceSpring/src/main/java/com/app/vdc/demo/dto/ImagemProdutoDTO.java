package com.app.vdc.demo.dto;

import lombok.*;
import org.springframework.lang.Nullable;

/*
* java.lang.IllegalArgumentException: Unrecognized field "nomeArquivo" (class com.app.vdc.demo.Model.ImagemProduto),
* not marked as ignorable (2 known properties: "id", "path"])
 at [Source: UNKNOWN; line: -1, column: -1] (through reference chain: com.app.vdc.demo.Model.ImagemProduto["nomeArquivo"])
* */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagemProdutoDTO {
    private int id;
//    private String nomeArquivo;
    private String path;
//    private String tipoArquivo;
//    private long tamanhoArquivo;

}

