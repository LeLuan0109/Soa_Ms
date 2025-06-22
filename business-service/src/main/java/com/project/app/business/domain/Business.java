package com.project.app.business.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "businesses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Business extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "business_id")
  private Integer businessId;

  private Integer orgId;

  @Column(name = "code_crawl", nullable = false, length = 255)
  private String codeCrawl;

  @Column(name = "business_code", nullable = false, length = 20)
  private String businessCode;

  @Column(name = "company_name", nullable = false, length = 100)
  private String companyName;

  @Column(name = "for_searching", length = 255)
  private String forSearching;

  @Column(name = "id_card", length = 20)
  private String idCard;

  @Column(name = "tax_code", nullable = false, length = 20)
  private String taxCode;

  @Column(name = "stock_code", nullable = false, length = 20)
  private String stockCode;

  @Column(name = "creator", length = 255)
  private String creator;

  @Column(name = "updator", length = 255)
  private String updator;

}
