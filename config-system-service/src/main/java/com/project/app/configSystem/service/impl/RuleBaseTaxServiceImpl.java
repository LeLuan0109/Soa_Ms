package com.project.app.configSystem.service.impl;

import com.project.app.configSystem.domain.RuleBase;
import com.project.app.configSystem.dto.RuleBaseDto;
import com.project.app.configSystem.repository.RuleBaseRepository;
import com.project.app.configSystem.request.RuleBaseFilter;
import com.project.app.configSystem.request.RuleBaseInfo;
import com.project.app.configSystem.service.RuleBaseTaxService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.util.SecurityUtils;
import com.project.app.core.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RuleBaseTaxServiceImpl implements RuleBaseTaxService {

  @Autowired
  private RuleBaseRepository ruleBaseRepository;

  @Override
  public Page<RuleBaseDto> filterRuleBaseTax(
      FilterPaging<RuleBaseFilter> filter) {
    return ruleBaseRepository.filterRuleBase(
        WebUtils.getOrgId(),
        filter.getFilter(),
        PageRequest.of(
            filter.getPageIndex(),
            filter.getPageSize()
        )
    );
  }

  @Override
  public RestHttpPostResponse createRuleBaseTax( RuleBaseInfo input) {
    boolean isExists = ruleBaseRepository.existsByRuleBaseKeyFields(
        WebUtils.getOrgId(),input.getRuleType(), input.getName());
    if (isExists) {
      throw new IllegalArgumentException("RuleBase already exists.");

    }
    RuleBase ruleBase = ruleBaseRepository.save(RuleBase.builder()
            .orgId(WebUtils.getOrgId())
            .ruleBaseName(input.getName())
            .type(input.getRuleType())
            .rankingDefault(input.getRanking())
            .status(input.getStatus())
            .creator(SecurityUtils.getUsername())
            .updator(SecurityUtils.getUsername())
        .build()
    );
    return new RestHttpPostResponse(ruleBase.getRuleBaseId().longValue());
  }

  @Override
  public RestHttpPostResponse updateRuleBaseTax(Integer id, RuleBaseInfo input) {
    RuleBase existing = ruleBaseRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("RuleBase not found with id: " + id));

    boolean isExists = ruleBaseRepository.existsByRuleBaseKeyFieldsExcludeId(
        id,
        WebUtils.getOrgId(),
        input.getRuleType(),
        input.getName()
    );
    if (isExists) {
      throw new IllegalArgumentException("Another RuleBase with same name and type already exists.");
    }

    existing.setRuleBaseName(input.getName());
    existing.setType(input.getRuleType());
    existing.setStatus(input.getStatus());
    existing.setRankingDefault(input.getRanking());

    ruleBaseRepository.save(existing);
    return new RestHttpPostResponse(existing.getRuleBaseId().longValue());
  }

  @Override
  public RestHttpPostResponse deleteRuleBaseTax(Integer id) {
    RuleBase existing = ruleBaseRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("RuleBase not found with id: " + id));

    ruleBaseRepository.delete(existing); // Nếu muốn xóa mềm thì set status = -1 thay vì delete
    return new RestHttpPostResponse(existing.getRuleBaseId().longValue());
  }


  @Override
  public RuleBaseDto getRuleBaseTax(Integer Id) {
    return ruleBaseRepository.findRuleBaseDtoById(Id)
        .orElseThrow(() -> new IllegalArgumentException("RuleBase not found with id: " + Id));
  }

}
