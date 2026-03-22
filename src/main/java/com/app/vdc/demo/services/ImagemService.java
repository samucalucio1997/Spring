package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.ImagemProduto;
import com.app.vdc.demo.dto.ImagemProdutoDTO;
import com.app.vdc.demo.utils.AwsService;
import com.app.vdc.demo.utils.ImageTransformerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemService implements ImagemIS {

    private final AwsService awsService;

    @Value("${chave-upload.aws.secret-key}")
    private String secretKey;


    @Override
    public void salvarImagem(List<MultipartFile> imgs, List<ImagemProduto> imagensProdutos) {
        imgs.stream().forEach(n -> {
            try {
                awsService.uploadFileToS3Bucket("bucket-imagens-estoque-gerencia", secretKey, n.getInputStream());
                final var imagemProdutoDTO = ImagemProdutoDTO.builder()
                        .path("https://bucket-imagens-estoque-gerencia.s3.amazonaws.com/" + n.getOriginalFilename())
                        .build();
                imagensProdutos.add(ImageTransformerUtils.imageDTOToImageDomain(imagemProdutoDTO));
            } catch (IllegalStateException e) {
                throw new IllegalStateException("erro no estado ao enviar o input stream para o S3", e);
            } catch (IOException e) {
                throw new RuntimeException("Houve um problema ao enviar o input stream para o S3", e);
            }
        });
    }
}
