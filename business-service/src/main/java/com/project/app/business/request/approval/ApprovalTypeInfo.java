package com.project.app.business.request.approval;

import com.project.app.business.request.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalTypeInfo extends StatusRequest {
  private String typeOption;

}
