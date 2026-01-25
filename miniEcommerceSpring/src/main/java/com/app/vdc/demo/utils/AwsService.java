package com.app.vdc.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;

@Component
public class AwsService {

    private static final Logger log = LoggerFactory.getLogger(AwsService.class);

    @Value("${bucket-aws.nome}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    public void uploadFileToS3Bucket(String bucketName,
                                              String key,
                                              InputStream inputStream
    ) {
        try{
            s3Client.putObject(
                    builder -> builder.bucket(bucketName).key(key).build(),
                    RequestBody.fromInputStream(inputStream, inputStream.available())
            );
        } catch (IOException e) {
            log.error("Erro ao fazer upload do arquivo para o S3", e);
            throw new RuntimeException(e);
        } catch(S3Exception e) {
            e.printStackTrace();
        }
    }

    public InputStreamResource getFileUrl(String nomeArquivo)  {
        try {
            final var fileRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(nomeArquivo)
                    .build();

            final var response = s3Client.getObjectAsBytes(fileRequest);

            return new InputStreamResource(response.asInputStream());
        }catch(S3Exception e) {
            throw e;
        }
    }
}
