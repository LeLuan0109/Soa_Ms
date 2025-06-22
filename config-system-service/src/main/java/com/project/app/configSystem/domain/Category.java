package com.project.app.configSystem.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "satus")
  private String status;

  @Column(name = "creator")
  private String creator;

  @Column(name = "updator")
  private String updator;
}
