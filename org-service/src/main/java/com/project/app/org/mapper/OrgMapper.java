package com.project.app.org.mapper;

import com.project.app.org.domain.TblOrg;
import com.project.app.org.dto.OrgDto;
import org.springframework.stereotype.Component;

@Component
public class OrgMapper {

  public OrgDto toDto(TblOrg tblOrg) {
    return new OrgDto(
        tblOrg.getId(),
        tblOrg.getName(),
        tblOrg.getDescription(),
        tblOrg.getAddress(),
        tblOrg.getCompanyName(),
        tblOrg.getEmail(),
        tblOrg.getPhone(),
        tblOrg.getLogo(),
        tblOrg.getTaxCode(),
        tblOrg.getWebsite(),
        tblOrg.getCreateTime(),
        tblOrg.getModifyTime()
    );
  }
}
