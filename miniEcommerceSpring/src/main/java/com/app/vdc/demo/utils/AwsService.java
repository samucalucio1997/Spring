package com.app.vdc.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;

@Component
public class AwsService {

    @Autowired
    private S3Client s3Client;

    public void uploadFileToS3Bucket(String bucketName,
                                              String key,
                                              InputStream inputStream
    ) {
        try{
//            S3Client s3Client = S3Client.create();

            s3Client.putObject(
                    builder -> builder.bucket(bucketName).key(key).build(),
                    RequestBody.fromInputStream(inputStream, inputStream.available())
            );
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
