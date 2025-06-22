package com.project.app.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
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
