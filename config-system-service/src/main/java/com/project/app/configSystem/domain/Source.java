package com.project.app.configSystem.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sources")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Source extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "source_id")
  private Integer sourceId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "source_name")
  private String sourceName;

  @Column(name = "url")
  private String url;

  @Column(name = "source")
  private String source;

  @Column(name = "source_type")
  private String sourceType;

  @Column(name = "satus")
  private String status;

  @Column(name = "creator")
  private String creator;

  @Column(name = "updator")
  private String updator;
}
