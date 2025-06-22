package com.project.app.user.client;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.app.exception.GenericErrorResponse;
import com.project.app.exception.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public Exception decode(String methodKey, Response response) {
    String clientName = methodKey.contains("#") ? methodKey.substring(0, methodKey.indexOf("#")) : methodKey;
    String methodName = methodKey.contains("#") ? methodKey.substring(methodKey.indexOf("#") + 1) : "";

    System.out.println("Feign Client: " + clientName);
    System.out.println("Method: " + methodName);

    // Tiếp tục xử lý lỗi như hiện tại
    try {
      if (response.body() == null) {
        // Xử lý trường hợp body null
        return new RuntimeException("Feign client error: response body is null. Client: "
            + clientName + ", Method: " + methodName);      }

      try (InputStream body = response.body().asInputStream()) {
        Map<String, String> errors =
            mapper.readValue(IOUtils.toString(body, StandardCharsets.UTF_8), Map.class);
        if (response.status() == 400) {
          return ValidationException.builder()
              .validationErrors(errors).build();
        } else
          return GenericErrorResponse
              .builder()
              .httpStatus(HttpStatus.valueOf(response.status()))
              .message(errors.get("error"))
              .build();
      }
    } catch (IOException exception) {
      return GenericErrorResponse.builder()
          .httpStatus(HttpStatus.valueOf(response.status()))
          .message(exception.getMessage())
          .build();
    }
  }

}