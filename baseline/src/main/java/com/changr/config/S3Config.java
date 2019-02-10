package com.changr.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 client() {
        AmazonS3Client amazonS3Client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
        return amazonS3Client;
    }

}
