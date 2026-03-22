package com.app.vdc.demo.utils;

import com.app.vdc.demo.Model.ImagemProduto;
import com.app.vdc.demo.dto.ImagemProdutoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImageTransformerUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static ImagemProduto imageDTOToImageDomain(ImagemProdutoDTO imgProduto) {
        return ImageTransformerUtils.objectMapper.convertValue(imgProduto, ImagemProduto.class);
    }
}
