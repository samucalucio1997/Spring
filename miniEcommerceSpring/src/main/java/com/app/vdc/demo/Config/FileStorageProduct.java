package com.app.vdc.demo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "archive")
public class FileStorageProduct {
    private String sobeImg;

    public String getSobeImg() {
        return sobeImg;
    }

    public void setSobeImg(String sobeImg) {
        this.sobeImg = sobeImg;
    }
    
}
