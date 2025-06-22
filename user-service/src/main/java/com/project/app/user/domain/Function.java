package com.project.app.user.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "function")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Function extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String code;
  private String label;
  private String icon;
  @Column(name = "parent_code")
  private String parentCode;
  private Integer sort;
  @Column(name = "router_link")
  private String routerLink;
  private Integer position;
  private Integer status;
  private String actions;
  @Column(name = "query_params")
  private String queryParams;
}
