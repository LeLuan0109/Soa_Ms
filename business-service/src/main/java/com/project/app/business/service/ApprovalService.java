package com.project.app.business.service;

import com.project.app.business.dto.approval.ApprovalBusinessDto;
import com.project.app.business.dto.approval.ApprovalInfoDto;
import com.project.app.business.dto.approval.ApprovalPostDto;
import com.project.app.business.request.approval.ApprovalBusinessFilter;
import com.project.app.business.request.approval.ApprovalInfo;
import com.project.app.business.request.approval.ApprovalPostFilter;
import com.project.app.business.request.approval.ApprovalTypeInfo;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import org.springframework.data.domain.Page;

public interface ApprovalService {

  Page<ApprovalBusinessDto>  filterApprovalBusinessTax(FilterPaging<ApprovalBusinessFilter> filterPaging);
  Page<ApprovalPostDto> filterApprovalPost(FilterPaging<ApprovalPostFilter> filterPaging);

  RestHttpPostResponse updateInfo(Integer id, ApprovalInfo input);
  RestHttpPostResponse updateStatus(Integer id, ApprovalTypeInfo inputStatus);

  ApprovalInfoDto getInfo(Integer id, ApprovalTypeInfo inputStatus);
}
