package com.project.app.configSystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleBaseInfo {
  private String name;
  private Integer ranking;
  private Integer status;
  private String ruleType;
}
