package com.app.vdc.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3ClientAWS {

    @Value("${AWS_REGION}")
    private String regionAWS;

    @Value("${chave-upload.aws.access-key}")
    private String accessKey;

    @Value("${chave-upload.aws.secret-key}")
    private String secretKey;

    @Bean
    public S3Client createS3Client() {
        final var region = Region.of(regionAWS);

        final var builder = S3Client.builder().region(region);

        if (accessKey != null && !accessKey.isEmpty() && secretKey != null && !secretKey.isEmpty()) {
            AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);
            builder.credentialsProvider(StaticCredentialsProvider.create(creds));
        }

        return builder.build();
    }
}
