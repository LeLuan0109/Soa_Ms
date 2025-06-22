package com.project.app.core.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class FileUploadConfig {

  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DataSize.ofMegabytes(500));
    factory.setMaxRequestSize(DataSize.ofMegabytes(500));

    return factory.createMultipartConfig();
  }
}