package com.app.vdc.demo.services;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.Model.ImagemProduto;
import com.app.vdc.demo.Model.Produto;
import com.app.vdc.demo.dto.ImagemProdutoDTO;
import com.app.vdc.demo.dto.ProdutoDTO;
import com.app.vdc.demo.repository.ImagemProdutoRepository;
import com.app.vdc.demo.repository.ProdutoRepository;
import com.app.vdc.demo.dto.ProdutoResponse;

import com.app.vdc.demo.utils.AwsService;
import com.app.vdc.demo.utils.ImageTransformerUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.app.vdc.demo.repository.custom.CustomRepositoryProdutoImp;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ProdutoService implements ProdutoIS{
    
    @Autowired
    private ProdutoRepository produtos;

    private final CustomRepositoryProdutoImp customRepositoryProdutoImp;

    @Autowired
    private ImagemProdutoRepository imgProduto;

    private final AwsService awsService;

    @Value("${chave-upload.aws.secret-key}")
    private String secretKey;

    @Autowired
    private ModelMapper modelMapper;

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
//        if (this.produtos.findById(id).isPresent()) {
//            final var produutosDto = ProdutoDTO.builder().build();
//
//            Produto produto = this.produtos.getById(id);
//            List<byte[]> imgs =new ArrayList<>();
//            produto.getImagens().stream().forEach( img -> {
//                Path caminho = this.caminho.resolve(img.getPath()).toAbsolutePath().normalize();
//                try {
//                    imgs.add(Files.readAllBytes(caminho));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            return new ProdutoResponse(imgs, produto);
//        }else{
//            return new ProdutoResponse(null, null);
//        }
        return new ProdutoResponse(null, null);
    }

    @Transactional
    @Override
    public boolean CadastrarProduto(ProdutoDTO produto,List<MultipartFile> imgs) throws IOException{
        try {
            Produto pro = produtos.findAll().stream().filter(n -> n.getNome().equals(produto.getNome())
            &&n.getCategoria().equals(produto.getCategoria())).findAny().orElse(null);
            final var listaImagens = new ArrayList<ImagemProduto>();

            if (pro!=null) {
               throw new RuntimeException("Já existe esse Produto");
            }

            if (!imgs.isEmpty()) {
               imgs.stream().forEach(n -> {//TODO: ao ínves de salvar o caminho do arquivo, salvar a chave
                try {
                    awsService.uploadFileToS3Bucket("bucket-imagens-estoque-gerencia", secretKey, n.getInputStream());
                    final var imagemProdutoDTO = ImagemProdutoDTO.builder()
                            .path("https://bucket-imagens-estoque-gerencia.s3.amazonaws.com/" + n.getOriginalFilename())
                            .build();
                    listaImagens.add(ImageTransformerUtils.imageDTOToImageDomain(imagemProdutoDTO));
                } catch (IllegalStateException e) {
                    throw new IllegalStateException("erro no estado ao enviar o input stream para o S3", e);
                } catch (IOException e) {
                    throw new RuntimeException("Houve um problema ao enviar o input stream para o S3", e);
                }
               });
            }
            final var produtoDomain = modelMapper.map(produto, Produto.class);

            if (!listaImagens.isEmpty()){
                produtoDomain.setImagens(listaImagens);
            }

            return this.produtos.save(produtoDomain) != null;
        } catch (Exception e) {
           throw e;
        }
    }

    @Override
    public Page<ProdutoDTO> ListarPro(String categoria,
                                      Double precoMin,
                                      Double precoMax,
                                      Pageable pageable) {

        final var categoriaStr = categoria == null || categoria.isBlank() ? null : categoria;
        final var precoMinFloat = precoMin == 0.0 ? null : precoMin.floatValue();
        final var precoMaxFloat = precoMax == 0.0 ? null : precoMax.floatValue();

        Page<Produto> produtosFiltrados = this.produtos.findByFilter(categoriaStr != null ? Categorias.valueOf(categoriaStr) : null
                , precoMinFloat, precoMaxFloat, pageable);
        final var responseContent = produtosFiltrados.getContent();
        final var listResult = responseContent.stream().map(p -> modelMapper.map(p, ProdutoDTO.class)).toList();

        return new PageImpl<ProdutoDTO>(listResult, pageable, produtosFiltrados.getTotalElements());
    }

    @Override
    public boolean EditarProduto(String nomeProduto, List<MultipartFile> imgs, Produto produto) {
        // TODO Auto-generated method stub
//        Produto produtoAtual = this.produtos
//        .findByNome(nome_produto);
//        if (!imgs.isEmpty()) {
//            imgs.stream().forEach(
//                n -> {
//                String cami = n.getOriginalFilename();
//                Path path = this.caminho.resolve(cami).toAbsolutePath().normalize();
//               try {
//                n.transferTo(path);
//                ImagemProduto pImpr = new ImagemProduto();
//                pImpr.setPath(cami);
//                this.imgProduto.save(pImpr);
//                produtoAtual.getImagens().add(pImpr);
//                this.produtos.save(produtoAtual);
//            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            });
//        }
        //produtoAtual.getImagens().
        return false;
    }
}
