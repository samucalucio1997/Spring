package com.app.vdc.demo.services.dto;

import java.util.List;

import com.app.vdc.demo.Model.Produto;

public record ProdutoResponse(List<byte[]> imgs,Produto produto) {

}
