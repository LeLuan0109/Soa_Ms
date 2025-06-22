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
public class TaxDebtInfoDto  {
  private long updated;
  private String debtAmount;
  private String debtClassification;
  private String typeOfDebt;
  private String taxPeriod;
  private Integer rankingAuto;
  private Integer rankingApproval;
  private String warningLevel;

  public TaxDebtInfoDto(LocalDateTime updated, String debtAmount, String debtClassification,
      String typeOfDebt, String taxPeriod, Integer rankingAuto, Integer rankingApproval,
      String warningLevel) {
    Instant instant = updated.atZone(ZoneId.systemDefault()).toInstant();
    this.updated =instant.toEpochMilli();
    this.debtAmount = debtAmount;
    this.debtClassification = debtClassification;
    this.typeOfDebt = typeOfDebt;
    this.taxPeriod = taxPeriod;
    this.rankingAuto = rankingAuto;
    this.rankingApproval = rankingApproval;
    this.warningLevel = warningLevel;
  }
}
