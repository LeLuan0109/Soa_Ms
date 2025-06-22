package com.project.app.user.domain;

import com.project.app.core.entity.BaseEntity;
import com.project.app.core.enums.Status;
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
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name= "code", length = 200)
  private String code;
  @Column(name = "name", length = 255)
  private String name;
  private Integer status;
  @Column(name = "roles", length = 255)
  private String roles;

  @Column(name = "creator", length = 200)
  private String creator;

  public Role(String name, String roles) {
    this.name = name;
    this.roles = roles;
    this.status = Status.ACTIVE.getCode();
  }

  public Role(String name, String roles, String creator) {
    this.name = name;
    this.roles = roles;
    this.creator = creator;
    this.status = Status.ACTIVE.getCode();
  }
}
