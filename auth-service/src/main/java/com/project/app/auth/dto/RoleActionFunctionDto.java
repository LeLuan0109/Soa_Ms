package com.project.app.auth.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleActionFunctionDto {
  private String code;
  private String label;
  private String icon;
  private int sort;
  private String routerLink;
  private String parentCode;
  private int position;
  private Map<String, Boolean> actions;
  private Map<String, Object> queryParams;
  private int status;
  private List<RoleActionFunctionDto> items;
}
