package com.project.app.org.service.Impl;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.org.dto.OrgRelationshipDto;
import com.project.app.org.dto.OrgDto;
import com.project.app.org.domain.TblOrg;

import com.project.app.org.mapper.OrgMapper;
import com.project.app.org.repository.TblOrgRepository;
import com.project.app.org.request.FilterOrg;
import com.project.app.org.service.IOrgService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrgServiceImpl implements IOrgService {

  @Autowired
  private TblOrgRepository tblOrgRepository;

  @Autowired
  private OrgMapper orgMapper;

  @Override
  public Page<OrgDto> filterOrganizations(FilterPaging<FilterOrg> filterPost) {
    return tblOrgRepository.search(
        filterPost.getFilter().getCompanyName(),
        PageRequest.of(
            filterPost.getPageIndex(),
            filterPost.getPageSize()
        )
    );
  }

  @Override
  public List<OrgRelationshipDto> getOrganizationRelationship(Integer orgId) {
    List<OrgRelationshipDto> data = new ArrayList<>();
    data.add(tblOrgRepository.getOrgById(orgId));
    return data;
  }

  @Override
  public List<Integer> getOrgIds() {
    return tblOrgRepository.getAllOrgIds();
  }

  @Override
  public List<OrgDto> fetchOrganizationsByIds(List<Integer> orgIds) {
    List<TblOrg> tblOrgs = tblOrgRepository.findByIdIn(orgIds);

    // Convert entities to DTOs using the mapper
    return tblOrgs.stream().map(orgMapper::toDto).toList();
  }

  @Override
  public List<OrgDto> getAllOrg() {
    return tblOrgRepository.getOrgDtoAll();
  }
}
