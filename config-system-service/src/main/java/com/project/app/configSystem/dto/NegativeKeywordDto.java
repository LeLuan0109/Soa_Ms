package com.project.app.configSystem.dto;

import com.project.app.core.common.dto.InfoUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegativeKeywordDto extends InfoUpdateDto {
  private Integer id;
  private String keyword;
  private String category;
  private Integer ranking;
  private Integer status;
}
