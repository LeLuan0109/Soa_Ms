package com.project.app.configSystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceInfo {
  private String name;
  private String source;
  private String url;
  private Integer status;
}
