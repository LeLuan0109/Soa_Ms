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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BusinessStatus entity represents both the operational status and the business condition of an enterprise.
 * It stores detailed status information such as status name, type, rankings, warning levels, and processing metadata.
 *
 * This entity links to the Business entity and reflects various stages of evaluation and approval.
 *
 * Author: LeLuan0109
 */
@Entity
@Table(name = "business_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessStatus extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "business_status_id")
  private Integer businessStatusId;

  @ManyToOne
  @JoinColumn(name = "business_id", nullable = false)
  private Business business;

  //map value with rulebase -> ranking
  @Column(name = "business_status")
  private String businessStatus;

  @Column(name = "previous_business_status")
  private String previousBusinessStatus;

  /**
   * Processing status of the recorded entry.
   * 0 - Pending Approval
   * 1 - Approved
   * 2 - Rejected
   */
  @Column(name = "status")
  private Integer status;

  @Column(name = "warning_level")
  private String warningLevel;

  @Column(name = "reason_change_rank")
  private String reasonChangeRank;

  @Column(name = "approver")
  private String approver;

  @Column(name = "associate")
  private String associate;

  @Column(name = "ranking_auto")
  private Integer rankingAuto;

  @Column(name = "ranking_approval")
  private Integer rankingApproval;
}
