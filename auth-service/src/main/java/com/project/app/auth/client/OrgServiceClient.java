package com.project.app.auth.client;

import com.project.app.auth.dto.OrgDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "org-service", path = "api/v1/organization")
public interface OrgServiceClient {

  @PostMapping("/system/getOrgByUserOrgIds")
  ResponseEntity<List<OrgDto>> getOrgByUserOrgIds(@RequestParam("orgIds")  List<Integer> orgIds);

}
