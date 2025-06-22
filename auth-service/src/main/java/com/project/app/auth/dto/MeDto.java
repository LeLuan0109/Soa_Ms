package com.project.app.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeDto {
  private Integer id;
  private String fullName;
  private String username;
  private String email;
  private String phone;
  private String address;
  private String birthday;
  private Integer active;
  private String gender;
  private String avatar;
  @JsonProperty("roles")
  private String roles;
  private String roleName;
  private Integer status;
  private long created;
  private Boolean admin;
  @JsonProperty("organization")
  private List<OrgDto> organizations;

  public static MeDto fromUser(AuthDto author) {
    return MeDto.builder()
        .id(author.getId())
        .fullName(author.getFullname())
        .phone(author.getPhone())
        .address(author.getAddress())
        .active(author.getIsActive() == true ? 1 : 0)
        .email(author.getEmail())
        .username(author.getUsername())
        .birthday(author.getBirthday())
        .roles(author.getRoles())
        .admin(author.getCode().equals("admin"))
        .build();
  }

  public MeDto(
      Integer id,
      String fullName,
      String username,
      String email,
      String phone,
      boolean active,
      LocalDateTime createTime
  ) {
    this.id = id;
    this.fullName = fullName;
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.status = active == true ? 1 : 0;
    Instant instant = createTime.atZone(ZoneId.systemDefault()).toInstant();
    this.created =instant.toEpochMilli();
  }
}
