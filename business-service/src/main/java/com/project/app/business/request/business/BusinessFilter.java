package com.project.app.business.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessFilter {
  private String taxCode;
  private String infoBusiness;
  private String stockCode;
  private Integer rank;
}
