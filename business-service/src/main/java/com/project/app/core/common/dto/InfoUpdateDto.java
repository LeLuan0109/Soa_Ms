package com.project.app.core.common.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InfoUpdateDto {
  private Long updated;
  private String updator;

  public InfoUpdateDto(LocalDateTime updated, String updator) {
    if(updated != null) {
      Instant instant = updated.atZone(ZoneId.systemDefault()).toInstant();
      this.updated =instant.toEpochMilli();
    }    this.updator = updator;
  }
}
