package com.project.app.user.service;

import com.project.app.user.dto.authUser.AuthUserDto;
import java.util.List;

public interface UserService {
  AuthUserDto getUserByUsername(String username);
  List<Integer> getOrgIdByUserId(Integer userId);
  Integer updateUser(AuthUserDto userDto);
  AuthUserDto getUserByEmail(String email);
  List<AuthUserDto> getAllUserAdmin();
  void UpdateUserOrg(List<Integer> userIds, List<Integer> orgIds);
}
