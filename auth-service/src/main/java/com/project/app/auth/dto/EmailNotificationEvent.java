package com.project.app.auth.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailNotificationEvent  {
  private String source;
  private EmailContentDto emailContent;
  private List<String> recipients;
  private String recipient;
  @NotNull
  private String template;
}
