package com.project.app.configSystem.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rule_bases")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleBase extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rule_base_id")
  private Integer ruleBaseId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "rule_base_name")
  private String ruleBaseName;

  @Column(name = "type")
  private String type;

  @Column(name = "ranking_default")
  private Integer rankingDefault;

  @Column(name = "status")
  private Integer status;

  @Column(name = "creator")
  private String creator;

  @Column(name = "updator")
  private String updator;
}
