package com.project.app.configSystem.Controller;

import com.project.app.configSystem.dto.RuleBaseDto;
import com.project.app.configSystem.request.RuleBaseFilter;
import com.project.app.configSystem.request.RuleBaseInfo;
import com.project.app.configSystem.service.RuleBaseTaxService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.request.IdInput;
import com.project.app.core.common.request.InfoInputInsert;
import com.project.app.core.common.request.InfoInputUpdate;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.PageInfoResponse;
import com.project.app.core.common.response.PagingResponse;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/rule-base")
public class RuleBaseController {

  @Autowired
  private  RuleBaseTaxService ruleBaseTaxService;

  @PostMapping("/filter")
  public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<RuleBaseDto>>>> filterRuleBaseTaxes(
      @RequestBody FilterPaging<RuleBaseFilter> filterRequest) {
    Page<RuleBaseDto> result = ruleBaseTaxService.filterRuleBaseTax( filterRequest);
    PageInfoResponse pageInfo = new PageInfoResponse(
        result.getPageable().getPageNumber(),
        result.getNumberOfElements(),
        result.getPageable().getPageNumber(),
        result.hasNext(),
        result.getTotalPages()
    );
    return ResponseEntity.ok(
        new DataResponse<>(new PagingResponse<>(result.getTotalElements(), pageInfo, result.getContent()))
    );
  }

  @PostMapping("/get-rule-base-detail")
  public ResponseEntity<DataResponse<RuleBaseDto>> getRuleBaseTaxDetail(@RequestBody IdInput<Integer> input) {
    return ResponseEntity.ok(new DataResponse<>(ruleBaseTaxService.getRuleBaseTax(input.getId())));
  }

  @PostMapping("/create")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> createRuleBaseTax(
      @RequestBody InfoInputInsert<RuleBaseInfo> input) {
    return ResponseEntity.ok(new DataResponse<>(ruleBaseTaxService.createRuleBaseTax( input.getInput())));
  }

  @PostMapping("/update")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateRuleBaseTax(
      @RequestBody InfoInputUpdate<Integer, RuleBaseInfo> input) {
    return ResponseEntity.ok(new DataResponse<>(ruleBaseTaxService.updateRuleBaseTax(input.getId(),  input.getInput())));
  }

  @PostMapping("/delete")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> deleteRuleBaseTax(@RequestBody IdInput<Integer> input) {
    return ResponseEntity.ok(new DataResponse<>(ruleBaseTaxService.deleteRuleBaseTax(input.getId())));
  }
}
