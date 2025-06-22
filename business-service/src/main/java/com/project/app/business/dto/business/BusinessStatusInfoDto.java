package com.project.app.business.dto.business;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessStatusInfoDto {
  private long updated;
  private String description;
  private Integer ranking;
  private String warningLevel;

  public BusinessStatusInfoDto(LocalDateTime updated, String description, Integer ranking, String warningLevel) {
    Instant instant = updated.atZone(ZoneId.systemDefault()).toInstant();
    this.updated =instant.toEpochMilli();
    this.description = description;
    this.ranking = ranking;
    this.warningLevel = warningLevel;
  }
}
