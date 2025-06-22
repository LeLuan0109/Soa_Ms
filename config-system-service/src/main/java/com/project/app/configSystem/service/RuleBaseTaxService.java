package com.project.app.configSystem.service;

import com.project.app.configSystem.dto.RuleBaseDto;
import com.project.app.configSystem.request.RuleBaseFilter;
import com.project.app.configSystem.request.RuleBaseInfo;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import org.springframework.data.domain.Page;

public interface RuleBaseTaxService {
  Page<RuleBaseDto> filterRuleBaseTax(
      FilterPaging<RuleBaseFilter> filter);

  RestHttpPostResponse createRuleBaseTax( RuleBaseInfo input);

  RestHttpPostResponse updateRuleBaseTax(Integer id,
      RuleBaseInfo input);

  RestHttpPostResponse deleteRuleBaseTax(Integer Id);

  RuleBaseDto getRuleBaseTax(Integer Id);
}