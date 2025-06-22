package com.project.app.org.service.Impl;

import com.project.app.org.domain.TblOrg;
import com.project.app.org.mapper.OrgMapper;
import com.project.app.org.repository.TblOrgRepository;
import com.project.app.org.service.IShareOrgService;
import com.project.app.org.dto.OrgDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShareOrgServiceImpl implements IShareOrgService {

  @Autowired
  private TblOrgRepository tblOrgRepository;

  @Autowired
  private OrgMapper orgMapper;

  @Override
  public List<OrgDto> fetchOrganizationsById(Integer orgIds) {
    return new ArrayList<>();
  }

  @Override
  public List<OrgDto> getOrganizations() {
    return tblOrgRepository.getOrgDtoAll();
  }

  @Override
  public OrgDto getOrgDtoById(Integer id) {
    return tblOrgRepository.findOrgDtoById(id)
        .orElseThrow(() -> new IllegalArgumentException("Organization not found with ID: " + id));
  }

  @Override
  public OrgDto getOrgDtoByCompanyName(String companyName) {
    return tblOrgRepository.findOrgDtoByCompanyName(companyName)
        .orElseThrow(() -> new IllegalArgumentException("Organization not found with company name: " + companyName));
  }

  @Override
  public List<Integer> convertStringToOrgIds(List<String> orgNames) {
    ArrayList<Integer> orgIds = new ArrayList<>();
    for (String s : orgNames) {
      Integer orgId = tblOrgRepository.findByName(s)
          .map(TblOrg::getId)
          .orElseThrow(() -> new IllegalArgumentException("Organization not found: " + s));
      orgIds.add(orgId);
    }
    return orgIds;
  }
}
