package com.project.app.user.controller;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.request.IdInput;
import com.project.app.core.common.request.InfoInputInsert;
import com.project.app.core.common.request.InfoInputUpdate;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.PageInfoResponse;
import com.project.app.core.common.response.PagingResponse;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.user.dto.role.RoleDto;
import com.project.app.user.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user-group")
@RequiredArgsConstructor
public class UserGroupController {

  private final RoleService roleService;

  @PostMapping("/filter-user-groups")
  ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<RoleDto>>>> filterUserGroups(@RequestBody FilterPaging<RoleDto> filterBase) {
    Page<RoleDto> result = this.roleService.filterRoles(filterBase);
    PageInfoResponse infoPageResponse = new PageInfoResponse(
        result.getPageable().getPageNumber(),
        result.getNumberOfElements(),
        result.getPageable().getPageNumber(),
        result.hasNext(),
        result.getTotalPages()
    );
    return ResponseEntity.ok(
        new DataResponse<>(
            new PagingResponse<>(
                result.getTotalElements(),
                infoPageResponse,
                result.getContent()
            )
        )
    );
  }

  @PostMapping("/get-user-group-detail")
  ResponseEntity<DataResponse<RoleDto>> getUserGroupDetail(@RequestBody IdInput<Integer> input){
    return  ResponseEntity.ok(
        new DataResponse<>(
            roleService.getRoleDetail(input.getId())
        )
    );
  }

  @PostMapping("/create-user-group")
  ResponseEntity<DataResponse<RestHttpPostResponse>> createUserGroup(@RequestBody InfoInputInsert<RoleDto> input){
    return  ResponseEntity.ok(
        new DataResponse<>(
            roleService.createRole(input.getInput())
        )
    );
  }

  @PostMapping("/update-user-group")
  ResponseEntity<DataResponse<RestHttpPostResponse>> updateUserGroup(@RequestBody InfoInputUpdate<Integer ,RoleDto> input)
      throws DataNotFoundException {
    return  ResponseEntity.ok(
        new DataResponse<>(
            roleService.updateRole(input.getId(),input.getInput())
        )
    );
  }

  @PostMapping("/delete-user-group")
  ResponseEntity<DataResponse<RestHttpPostResponse>> deleteUserGroup(@RequestBody IdInput<Integer> input)
      throws DataNotFoundException {
    return  ResponseEntity.ok(
        new DataResponse<>(
            roleService.deleteRole(input.getId())
        )
    );
  }
}
