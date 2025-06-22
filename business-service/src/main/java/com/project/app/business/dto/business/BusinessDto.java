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
public class BusinessDto {
  private Integer id;
  private String businessCode;
  private String companyName;
  private String taxCode;
  private String stockCode;
  private String operationStatus;
  private Integer status;
  private Integer taxRanking;
  private Long updated;
  private String idCard;
  private String forSearching;

  public BusinessDto(Integer id, String businessCode, String companyName, String taxCode,
      String stockCode, String operationStatus, Integer status, Integer taxRanking, LocalDateTime updated) {
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.operationStatus = operationStatus;
    this.status = status;
    this.taxRanking = taxRanking;
    if(updated != null) {
      Instant instant = updated.atZone(ZoneId.systemDefault()).toInstant();
      this.updated =instant.toEpochMilli();
    }
  }

  public BusinessDto(Integer id, String businessCode, String companyName, String taxCode,
      String stockCode, String operationStatus, Integer status, Integer taxRanking,
      String idCard, String forSearching) {
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.operationStatus = operationStatus;
    this.status = status;
    this.taxRanking = taxRanking;
    this.idCard = idCard;
    this.forSearching = forSearching;
  }
}
