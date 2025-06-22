package com.project.app.business.request.approval;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalPostFilter {
  private Long startDate;
  private Long endDate;
  private List<String> sources;
  private List<String> domains;
  private Integer ranking;
  private String keyword;
  private Integer completed;
  private Integer spam;
  private List<Integer> status;
}
