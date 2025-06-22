package com.project.app.configSystem.dto;

import com.project.app.core.common.dto.InfoUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDto extends InfoUpdateDto {
  private Integer id;
  private String name;
  private String source;
  private String url;
  private Integer status;
}
