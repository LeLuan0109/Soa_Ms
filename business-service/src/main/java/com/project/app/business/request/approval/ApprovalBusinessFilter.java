package com.project.app.business.request.approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalBusinessFilter {
  public String status;
  public String name;
  private String typeOption;
}
