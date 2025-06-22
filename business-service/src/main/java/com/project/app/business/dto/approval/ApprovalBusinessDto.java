package com.project.app.business.dto.approval;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovalBusinessDto extends TaxDebtInfoDto {
  private Integer id;
  private String businessCode;
  private String companyName;
  private String taxCode;
  private String stockCode;
  private String reason;
  private Integer status;

  public ApprovalBusinessDto(LocalDateTime updated, String debtAmount, String debtClassification,
      String typeOfDebt, String taxPeriod, Integer rankingAuto, Integer rankingApproval,
      String warningLevel, Integer id, String businessCode, String companyName, String taxCode,
      String stockCode, String reason, Integer status) {
    super(updated, debtAmount, debtClassification, typeOfDebt, taxPeriod, rankingAuto,
        rankingApproval, warningLevel);
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.reason = reason;
    this.status = status;
  }

  public ApprovalBusinessDto(Integer id, String businessCode, String companyName, String taxCode,
      String stockCode, String reason) {
    this.id = id;
    this.businessCode = businessCode;
    this.companyName = companyName;
    this.taxCode = taxCode;
    this.stockCode = stockCode;
    this.reason = reason;
  }
}
