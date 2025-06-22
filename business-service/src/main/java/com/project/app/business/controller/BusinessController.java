package com.project.app.business.controller;

import com.project.app.business.dto.business.BusinessDto;
import com.project.app.business.request.business.BusinessInfo;
import com.project.app.business.request.business.BusinessFilter;
import com.project.app.business.request.business.BusinessInfoStatus;
import com.project.app.business.service.BusinessService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.request.IdInput;
import com.project.app.core.common.request.InfoInputUpdate;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.PageInfoResponse;
import com.project.app.core.common.response.PagingResponse;
import com.project.app.core.common.response.RestHttpPostResponse;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.prefix}/business")
public class BusinessController {

  @Autowired
  private BusinessService businessService;

  @PostMapping("/filter")
  public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<BusinessDto>>>> filterBusiness(
      @RequestBody FilterPaging<BusinessFilter> filterPaging){
    Page<BusinessDto> data = businessService.filter(filterPaging);
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

  @PostMapping("/create")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> createBusiness(@Valid @RequestBody BusinessInfo input) {
    return ResponseEntity.ok(DataResponse.<RestHttpPostResponse>builder().data(businessService.creat(input)).build());
  }

  @PostMapping("/update")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateBusiness(@Valid @RequestBody InfoInputUpdate<Integer, BusinessInfo> input) {
    return ResponseEntity.ok(DataResponse.<RestHttpPostResponse>builder().data(businessService.update(input.getId(), input.getInput())).build());
  }

  @PostMapping("/update-status")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateBusinessStatus(@Valid @RequestBody InfoInputUpdate<Integer, BusinessInfoStatus> input) {
    return ResponseEntity.ok(DataResponse.<RestHttpPostResponse>builder().data(businessService.updateStatus(input.getId(), input.getInput())).build());
  }

  @PostMapping("/get-business-detail")
  public ResponseEntity<DataResponse<BusinessDto>> getBusinessDetail(@Valid @RequestBody IdInput<Integer> input) {
    return ResponseEntity.ok(DataResponse.<BusinessDto>builder().data(businessService.getBusinessDetail(input.getId())).build());
  }

  @PostMapping("/upload-file")
  ResponseEntity<DataResponse<RestHttpPostResponse>> createFromExcelFile(@ModelAttribute MultipartFile file) throws Exception {
    return ResponseEntity.ok(
        new DataResponse<>(
            this.businessService.createFromExcelFile(file)
        )
    );
  }


}
