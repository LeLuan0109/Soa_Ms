  package com.project.app.core.common.request;

  import com.project.app.core.validation.ValidTimeRange;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class TimeRange {
    private Long startDate;
    private Long endDate;
  }
