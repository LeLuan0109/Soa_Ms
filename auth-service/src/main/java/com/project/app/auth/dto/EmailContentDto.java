package com.project.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailContentDto{
  private String title;
  private String content;
  private String url;
  private String pubTimeDate;
  private String pubTimeHour;
  private String pubTimeHouse;
}
