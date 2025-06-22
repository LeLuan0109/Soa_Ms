package com.project.app.configSystem.dto;

import com.project.app.core.common.dto.InfoUpdateDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RuleBaseDto extends InfoUpdateDto {
  private Integer id;
  private String name;
  private Integer ranking;
  private Integer status;

  public RuleBaseDto(Integer id, String name, Integer ranking, Integer status) {
    this.id = id;
    this.name = name;
    this.ranking = ranking;
    this.status = status;
  }

  public RuleBaseDto(LocalDateTime updated, String updator, Integer id, String name,
      Integer ranking, Integer status) {
    super(updated, updator);
    this.id = id;
    this.name = name;
    this.ranking = ranking;
    this.status = status;
  }
}
