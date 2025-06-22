package com.project.app.configSystem.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "negative_keyword")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegativeKeyword extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "negative_keyword_id")
  private Integer negativeKeywordId;

  @Column(name = "org_id")
  private Integer orgId;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "negative_keyword_name")
  private String negativeKeywordName;

  @Column(name = "negative_keyword_type")
  private String negativeKeywordType;

  @Column(name = "satus")
  private String status;

  @Column(name = "creator")
  private String creator;

  @Column(name = "updator")
  private String updator;
}
