package com.project.app.user.controller;

import com.project.app.user.dto.authUser.AuthUserDto;
import com.project.app.user.request.user.UpdateUserOrgRequest;
import com.project.app.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("system/getUserByUsername/{username}")
  public ResponseEntity<AuthUserDto> getUserByUsername(@PathVariable String username) {
    AuthUserDto user = userService.getUserByUsername(username);
    return ResponseEntity.ok(user);
  }

  @GetMapping("system/getUserByEmail/{email}")
  public ResponseEntity<AuthUserDto> getUserByEmail(@PathVariable String email) {
    AuthUserDto user = userService.getUserByEmail(email);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/system/getOrgIdByUserId/{userId}")
  public ResponseEntity<List<Integer>> getUserByUsername(@PathVariable Integer userId) {
    List<Integer> orgIds = userService.getOrgIdByUserId(userId);
    return ResponseEntity.ok(orgIds);
  }

  @PostMapping("system/updateUser")
  public ResponseEntity<Integer> updateUser(@RequestBody AuthUserDto updateUser){
    Integer userId = userService.updateUser(updateUser);
    return ResponseEntity.ok(userId);
  }

  @GetMapping("/system/getAllUserAdmin")
  public ResponseEntity<List<AuthUserDto>> getAllUserAdmin() {
    List<AuthUserDto> allUserAdmin = userService.getAllUserAdmin();
    return ResponseEntity.ok(allUserAdmin);
  }

  @PostMapping("system/updateUserOrgAdmin")
  void updateUserOrgAdmin(@RequestBody UpdateUserOrgRequest updateUserOrgRequest){
    userService.UpdateUserOrg(updateUserOrgRequest.getUserIds(), updateUserOrgRequest.getOrgIds());
  }
}
