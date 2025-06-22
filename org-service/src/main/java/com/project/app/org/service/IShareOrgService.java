package com.project.app.org.service;

import com.project.app.org.dto.OrgDto;
import java.util.List;

public interface IShareOrgService {

  List<OrgDto> fetchOrganizationsById(Integer orgIds);
  List<OrgDto> getOrganizations();
  OrgDto getOrgDtoById(Integer id);
  OrgDto getOrgDtoByCompanyName(String companyName);
  List<Integer> convertStringToOrgIds(List<String> orgNames);

}
