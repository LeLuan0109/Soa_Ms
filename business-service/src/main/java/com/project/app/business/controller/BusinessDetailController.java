package com.project.app.business.controller;

import com.project.app.business.dto.business.BusinessStatusInfoDto;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import com.project.app.business.dto.post.PostDto;
import com.project.app.business.request.business.BusinessFilterInfoOption;
import com.project.app.business.request.post.PostFilter;
import com.project.app.business.service.BusinessDetailService;
import com.project.app.core.common.response.DataResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/business-detail")
public class BusinessDetailController {

  @Autowired
  private BusinessDetailService businessDetailService;

  @PostMapping("/get-business-status-info")
  public ResponseEntity<DataResponse<List<BusinessStatusInfoDto>>> getBusinessStatusInfo(
      @RequestBody BusinessFilterInfoOption inputOptionInfo) {
    return ResponseEntity.ok(
        DataResponse.<List<BusinessStatusInfoDto>>builder()
            .data(businessDetailService.getBusinessStatusInfo(inputOptionInfo))
            .build()
    );
  }

  @PostMapping("/get-tax-debt-info")
  public ResponseEntity<DataResponse<List<TaxDebtInfoDto>>> getTaxDebtInfo(
      @RequestBody BusinessFilterInfoOption inputOptionInfo) {
    return ResponseEntity.ok(
        DataResponse.<List<TaxDebtInfoDto>>builder()
            .data(businessDetailService.getTaxDebtInfo(inputOptionInfo))
            .build()
    );
  }

  @PostMapping("/get-post-info")
  public ResponseEntity<DataResponse<List<PostDto>>> getPostInfo(
      @RequestBody PostFilter inputOptionInfo) {
    return ResponseEntity.ok(
        DataResponse.<List<PostDto>>builder()
            .data(businessDetailService.getPostInfo(inputOptionInfo))
            .build()
    );
  }
}
