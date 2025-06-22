package com.project.app.business.dto.approval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalInfoDto {
  private Integer id;
  private String reason;
  private Integer rankingApproval;
  private Integer rankingAuto;
}
