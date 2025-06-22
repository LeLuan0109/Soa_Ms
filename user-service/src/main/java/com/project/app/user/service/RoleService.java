package com.project.app.user.service;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.user.dto.role.ActionDto;
import com.project.app.user.dto.role.FunctionDto;
import com.project.app.user.dto.role.RoleDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface RoleService {
  List<FunctionDto> getAllFunction();
  List<ActionDto> getAllAction();
  List<RoleDto> getAllRoles();

  Page<RoleDto> filterRoles(FilterPaging<RoleDto> filterBase);

  RoleDto getRoleDetail(Integer id);

  RestHttpPostResponse createRole(RoleDto input);

  RestHttpPostResponse updateRole(Integer id, RoleDto input) throws DataNotFoundException;

  RestHttpPostResponse deleteRole(Integer id) throws DataNotFoundException;
}
