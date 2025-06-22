package com.project.app.user.request.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserOrgRequest {
  private List<Integer> userIds;
  private List<Integer> orgIds;
}
