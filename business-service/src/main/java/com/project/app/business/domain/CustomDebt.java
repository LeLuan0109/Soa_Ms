package com.project.app.business.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_debt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomDebt extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "custom_debt_id")
  private Integer customDebtId;

  @ManyToOne
  @JoinColumn(name = "business_id", nullable = false)
  private Business business;

  //map value with rulebase -> ranking
  @Column(name = "custom_debt_classification", length = 255)
  private String customDebtClassification;

  @Column(name = "debt_amount")
  private String debtAmount;

  @Column(name = "type_of_debt", length = 100)
  private String typeOfDebt;

  /**
   * Processing status of the recorded entry.
   * 0 - Pending Approval
   * 1 - Approved
   * 2 - Rejected
   */
  @Column(name = "status", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
  private Integer status;

  @Column(name = "warning_level", length = 50)
  private String warningLevel;

  @Column(name = "approver", length = 100)
  private String approver;

  @Column(name = "associate", length = 100)
  private String associate;

  @Column(name = "ranking_auto", nullable = false)
  private Integer rankingAuto;

  @Column(name = "ranking_approval")
  private Integer rankingApproval;

  @Column(name = "reason_change_rank", length = 255)
  private String reasonChangeRank;

}