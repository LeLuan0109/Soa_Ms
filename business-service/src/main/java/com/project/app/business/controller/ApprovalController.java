package com.project.app.business.controller;

import com.project.app.business.dto.approval.ApprovalBusinessDto;
import com.project.app.business.dto.approval.ApprovalInfoDto;
import com.project.app.business.dto.approval.ApprovalPostDto;
import com.project.app.business.request.approval.ApprovalBusinessFilter;
import com.project.app.business.request.approval.ApprovalInfo;
import com.project.app.business.request.approval.ApprovalPostFilter;
import com.project.app.business.request.approval.ApprovalTypeInfo;
import com.project.app.business.service.ApprovalService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.request.InfoInputUpdate;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.PageInfoResponse;
import com.project.app.core.common.response.PagingResponse;
import com.project.app.core.common.response.RestHttpPostResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/approval")
public class ApprovalController {
  @Autowired
  private ApprovalService approvalService;

  @PostMapping("/filter-approval-business-tax")
  public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<ApprovalBusinessDto>>>> filterApprovalBusinessTax(
      @RequestBody FilterPaging<ApprovalBusinessFilter> filterPaging){
    Page<ApprovalBusinessDto> data = approvalService.filterApprovalBusinessTax(filterPaging);
    PagingResponse result = new PagingResponse(
        data.getTotalElements(),
        new PageInfoResponse(
            data.getPageable().getPageNumber(),
            data.getNumberOfElements(),
            data.getPageable().getPageNumber(),
            data.hasNext(),
            data.getTotalPages()
        ),
        data.getContent()
    );
    return ResponseEntity.ok(
        new DataResponse<>(result));
  }

  @PostMapping("/filter-approval-business-post")
  public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<ApprovalPostDto>>>> filterApprovalPost(
      @RequestBody FilterPaging<ApprovalPostFilter> filterPaging){
    Page<ApprovalPostDto> data = approvalService.filterApprovalPost(filterPaging);
    PagingResponse result = new PagingResponse(
        data.getTotalElements(),
        new PageInfoResponse(
            data.getPageable().getPageNumber(),
            data.getNumberOfElements(),
            data.getPageable().getPageNumber(),
            data.hasNext(),
            data.getTotalPages()
        ),
        data.getContent()
    );
    return ResponseEntity.ok(
        new DataResponse<>(result));
  }

  @PostMapping("/update-approval")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateInfo(@RequestBody InfoInputUpdate<Integer, ApprovalInfo> input) {
    return ResponseEntity.ok(DataResponse.<RestHttpPostResponse>builder().data(approvalService.updateInfo(input.getId(), input.getInput())).build());
  }

  @PostMapping("/update-status")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateStatus(@RequestBody InfoInputUpdate<Integer, ApprovalTypeInfo> input) {
    return ResponseEntity.ok(DataResponse.<RestHttpPostResponse>builder().data(approvalService.updateStatus(input.getId(), input.getInput())).build());
  }

  @PostMapping("/get-info")
  public ResponseEntity<DataResponse<ApprovalInfoDto>> getInfo(@RequestBody InfoInputUpdate<Integer, ApprovalTypeInfo> input) {
    return ResponseEntity.ok(DataResponse.<ApprovalInfoDto>builder().data(approvalService.getInfo(input.getId(), input.getInput())).build());
  }
}
