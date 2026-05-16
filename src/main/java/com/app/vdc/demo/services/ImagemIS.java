package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.ImagemProduto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagemIS {

	public void salvarImagem(List<MultipartFile> imgs, List<ImagemProduto> imagensProdutos);

}
