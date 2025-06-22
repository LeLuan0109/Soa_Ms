package com.project.app.org.controller;


import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.PageInfoResponse;
import com.project.app.core.common.response.PagingResponse;
import com.project.app.core.util.WebUtils;
import com.project.app.org.dto.OrgDto;
import com.project.app.org.dto.OrgRelationshipDto;
import com.project.app.org.service.IShareOrgService;
import com.project.app.org.request.FilterOrg;
import com.project.app.org.service.IOrgService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organization")
public class OrgController {
  @Autowired
  private IOrgService orgService;
  @Autowired
  private IShareOrgService shareOrgService;

  @PostMapping("/filter-organizations")
  public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<OrgDto>>>> filterOrganizations(@RequestBody FilterPaging<FilterOrg> organizationInput) {
    Page<OrgDto> result = this.orgService.filterOrganizations(organizationInput);
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

  @PostMapping("/get-organization-relationship")
  public ResponseEntity<DataResponse<List<OrgRelationshipDto>>> getOrganizationRelationship() {
    return ResponseEntity.ok(
        new DataResponse<>(
            this.orgService.getOrganizationRelationship(WebUtils.getOrgId())
        )
    );
  }

  @PostMapping("system/getOrgByUserOrgIds")
  public ResponseEntity<List<OrgDto>>getOrgByUserOrgIds(@RequestParam List<Integer> orgIds){
    return ResponseEntity.ok(
        orgService.fetchOrganizationsByIds(orgIds)
    );
  }

  @GetMapping("system/getAllOrg")
  public ResponseEntity<List<OrgDto>>getAllOrg(){
    return ResponseEntity.ok(
        orgService.getAllOrg()
    );
  }
}
