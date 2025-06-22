package com.project.app.org.service;


import com.project.app.core.common.request.FilterPaging;
import com.project.app.org.dto.OrgRelationshipDto;
import com.project.app.org.dto.OrgDto;
import com.project.app.org.request.FilterOrg;
import java.util.List;
import org.springframework.data.domain.Page;

public interface IOrgService {
  Page<OrgDto> filterOrganizations(FilterPaging<FilterOrg> filterPost);
  List<OrgDto> fetchOrganizationsByIds(List<Integer> orgIds);
  List<OrgDto> getAllOrg();

  List<OrgRelationshipDto> getOrganizationRelationship(Integer orgId);

  List<Integer> getOrgIds();
}

