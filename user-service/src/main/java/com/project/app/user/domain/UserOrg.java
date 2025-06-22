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
@Table(name = "user_org")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrg extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "org_id")
  private Integer orgId;

  public UserOrg(Integer userId, Integer orgId) {
    this.userId = userId;
    this.orgId = orgId;
  }
}
