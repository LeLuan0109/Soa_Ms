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

@Entity
@Table(name = "trans_detail_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransDetailPost extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trans_detail_posts_id")
  private Integer transDetailPostsId;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

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
