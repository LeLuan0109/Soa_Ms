package com.project.app.user.service.impl;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.core.exception.exceptionSub.RuntimeExceptionSls;
import com.project.app.core.helper.DateTimeHelper;
import com.project.app.core.util.SecurityUtils;
import com.project.app.user.domain.Role;
import com.project.app.user.dto.role.ActionDto;
import com.project.app.user.dto.role.FunctionDto;
import com.project.app.user.dto.role.RoleDto;
import com.project.app.user.repository.ActionRepository;
import com.project.app.user.repository.FunctionRepository;
import com.project.app.user.repository.RoleRepository;
import com.project.app.user.service.RoleService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
  @Autowired
  private FunctionRepository functionRepository;
  @Autowired
  private ActionRepository actionRepository;
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<FunctionDto> getAllFunction() {
    return functionRepository.getAllActionFunction();
  }

  @Override
  public List<ActionDto> getAllAction() {
    return actionRepository.getAllAction();
    }

  @Override
  public List<RoleDto> getAllRoles() {
    return roleRepository.getAllRoles();
  }

  @Override
  public Page<RoleDto> filterRoles(FilterPaging<RoleDto> filterBase) {
    RoleDto filter = filterBase.getFilter();
    String name = Optional.ofNullable(filter.getName()).filter(s -> !s.isEmpty()).orElse(null);
    String creator = Optional.ofNullable(filter.getCreator()).filter(s -> !s.isEmpty()).orElse(null);
    return roleRepository.filterRoles(
        name,
        DateTimeHelper.toLocalDate(filter.getCreated()),
        creator,
        PageRequest.of(
            filterBase.getPageIndex(),
            filterBase.getPageSize(),
            Sort.sort(Role.class).by(Role::getCreateTime).descending())
    );
  }
  @Override
  public RoleDto getRoleDetail(Integer id) {
    return roleRepository.getRoleDetail(id)
        .orElseThrow(() -> new RuntimeExceptionSls("Role not found"));
  }

  @Override
  public RestHttpPostResponse createRole(RoleDto input) {
    Role role = new Role(input.getName(), input.getRoles(), SecurityUtils.getUsername());
    roleRepository.save(role);
    return new RestHttpPostResponse(role.getId().longValue());
  }

  @Override
  public RestHttpPostResponse updateRole(Integer id, RoleDto input) throws DataNotFoundException {
    Role role = roleRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException("Role not found"));

    role.setName(input.getName());
    role.setRoles(input.getRoles());
    roleRepository.save(role);

    return new RestHttpPostResponse(role.getId().longValue());
  }

  @Override
  public RestHttpPostResponse deleteRole(Integer id) throws DataNotFoundException {
    Role role = roleRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException("Role not found"));

    roleRepository.delete(role);
    return new RestHttpPostResponse(id.longValue());
  }
}
