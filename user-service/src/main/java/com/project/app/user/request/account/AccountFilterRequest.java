package com.project.app.user.request.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFilterRequest {
  private String fullName;
  private String creator;
  private Long created;
  private String username;
  private Integer status;
  private Integer userId;
}
