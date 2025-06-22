package com.project.app.user.dto.role;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
  private Integer id;
  private String name;
  private String code;
  private String roles;
  private Long created;
  private String creator;

  public RoleDto( LocalDateTime created,String creator, String roles, String code, String name, Integer id) {
    Instant instant = created.atZone(ZoneId.systemDefault()).toInstant();
    this.created =instant.toEpochMilli();
    this.creator = creator;
    this.roles = roles;
    this.code = code;
    this.name = name;
    this.id = id;
  }
}
