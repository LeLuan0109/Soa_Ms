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
@Table(name = "role_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "role_id")
  private Integer roleId;

  public RoleUser(Integer userId, Integer roleId) {
    this.userId = userId;
    this.roleId = roleId;
  }
}
