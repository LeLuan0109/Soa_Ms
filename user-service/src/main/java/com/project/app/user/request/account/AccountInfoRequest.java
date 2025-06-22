package com.project.app.user.request.account;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoRequest {
  @NonNull
  private String username;
  @NonNull
  private String password;
  private String avatar;
  @NonNull
  private String email;
  private String address;
  @NonNull
  private String fullName;
  private String phone;
  private Integer gender;
  private Integer birthday;
  @NonNull
  private Integer role;
  @NonNull
  private String passwordConfirmation;
  private List<Integer> organization;
}
