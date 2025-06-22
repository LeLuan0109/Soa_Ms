package com.project.app.auth.client;

import com.project.app.auth.dto.AuthDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "api/v1/user")
public interface UserServiceClient {

  @GetMapping("/system/getUserByUsername/{username}")
  ResponseEntity<AuthDto> getUserByUsername(@PathVariable String username);

  @GetMapping("/system/getOrgIdByUserId/{userId}")
  ResponseEntity<List<Integer>> getOrgIdByUserId(@PathVariable Integer userId);

  @PostMapping("system/updateUser")
  ResponseEntity<Integer> updateUser(@RequestBody AuthDto updateUser);

  @GetMapping("/system/getUserByEmail/{email}")
  ResponseEntity<AuthDto> getUserByEmail(@PathVariable String email);
}
