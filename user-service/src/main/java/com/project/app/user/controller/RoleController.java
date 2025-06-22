package com.project.app.user.controller;

import com.project.app.core.common.response.DataResponse;
import com.project.app.user.dto.role.ActionDto;
import com.project.app.user.dto.role.FunctionDto;
import com.project.app.user.dto.role.RoleDto;
import com.project.app.user.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/get-all-role")
  public ResponseEntity<DataResponse<List<RoleDto>>> getAllRole(){
    return ResponseEntity.ok(
        new DataResponse<>(
            this.roleService.getAllRoles()
        )
    );
  }

  @GetMapping("/get-all-function")
  public ResponseEntity<DataResponse<List<FunctionDto>>> getAllFunction() {
    return ResponseEntity.ok(
        new DataResponse<>(
            this.roleService.getAllFunction()
        )
    );
  }

  @GetMapping("/get-all-action")
  public ResponseEntity<DataResponse<List<ActionDto>>> getAllAction() {
    return ResponseEntity.ok(
        new DataResponse<>(
            this.roleService.getAllAction()
        )
    );
  }
}
