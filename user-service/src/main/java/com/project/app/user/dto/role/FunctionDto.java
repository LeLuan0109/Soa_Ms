package com.project.app.user.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionDto {
  private String code;
  private String label;
  private String icon;
  private Integer sort;
  private String routerLink;
  private String parentCode;
  private Integer position;
  private Integer status;
  private String actions;
  private String queryParams;
}
