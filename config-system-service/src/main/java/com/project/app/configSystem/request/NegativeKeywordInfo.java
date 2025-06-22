package com.project.app.configSystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegativeKeywordInfo {
  private String keyword;
  private String category;
  private Integer ranking;
  private Integer status;
}
