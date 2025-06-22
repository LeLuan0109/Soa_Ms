package com.project.app.core.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
  private static final String FEIGN_REQUEST_HEADER = "X-Feign-Request";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header(FEIGN_REQUEST_HEADER, "true");
  }
}
