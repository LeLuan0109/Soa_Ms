package com.project.app.user.dto.authUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDto {
  private Integer id;
  private String roles;
  private String username;
  private String password;
  private Boolean isActive;
  private String fullname;
  private Integer userVersion;
  private String email;
  private String phone;
  private String birthday;
  private String address;
  private String code;
}
