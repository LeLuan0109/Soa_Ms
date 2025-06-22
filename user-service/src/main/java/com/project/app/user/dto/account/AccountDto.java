package com.project.app.user.dto.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.user.domain.User;
import com.project.app.user.dto.OrgDto;
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
public class AccountDto {

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

  public static AccountDto fromUser(User author) {
    return AccountDto.builder()
        .id(author.getId())
        .fullName(author.getFullname())
        .phone(author.getPhone())
        .address(author.getAddress())
        .active(author.getIsActive() == true ? 1 : 0)
        .email(author.getEmail())
        .username(author.getUsername())
        .birthday(author.getBirthday())
        .gender(null)
        .avatar(null)
        .build();
  }

  public AccountDto(
      Integer id,
      String fullName,
      String username,
      String email,
      String phone,
      Boolean active,
      LocalDateTime created
  ) {
    this.id = id;
    this.fullName = fullName;
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.status = active == true ? 1 : 0;
    Instant instant = created.atZone(ZoneId.systemDefault()).toInstant();
    this.created =instant.toEpochMilli();
  }
}
