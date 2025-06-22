package com.project.app.business.service.impl;

import static com.project.app.business.converter.PostConverter.convertSortForQuery;

import com.project.app.business.domain.enums.BusinessType;
import com.project.app.business.dto.business.BusinessStatusInfoDto;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import com.project.app.business.dto.post.PostDto;
import com.project.app.business.repository.BusinessRepository;
import com.project.app.business.repository.BusinessStatusRepository;
import com.project.app.business.repository.CustomDebtRepository;
import com.project.app.business.repository.PostRepository;
import com.project.app.business.repository.PostRepositoryCustom;
import com.project.app.business.repository.TaxBranchDebtRepository;
import com.project.app.business.repository.TaxStatusRepository;
import com.project.app.business.request.business.BusinessFilterInfoOption;
import com.project.app.business.request.post.PostFilter;
import com.project.app.business.service.BusinessDetailService;
import com.project.app.core.util.WebUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BusinessDetailServiceImpl implements BusinessDetailService {

  @Autowired
  private BusinessRepository businessRepository;

  @Autowired
  private BusinessStatusRepository businessStatusRepository;

  @Autowired
  private TaxStatusRepository taxStatusRepository;

  @Autowired
  private CustomDebtRepository customDebtRepository;

  @Autowired
  private TaxBranchDebtRepository taxBranchDebtRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private PostRepositoryCustom postRepositoryCustom;

  @Override
  public List<BusinessStatusInfoDto> getBusinessStatusInfo(
      BusinessFilterInfoOption inputOptionInfo) {
    switch (BusinessType.fromValue(inputOptionInfo.getTypeOption())) {
      case TAX_STATUS:
        return taxStatusRepository.getBusinessForTaxStatus(
            WebUtils.getOrgId(),
            inputOptionInfo.getBusinessId(),
            inputOptionInfo.getTop()
        );
      case BUSINESS_STATUS:
        return businessStatusRepository.getBusinessForBusinessStatus(
            WebUtils.getOrgId(),
            inputOptionInfo.getBusinessId(),
            inputOptionInfo.getTop()
        );
      default:
        return List.of();
    }
  }

  @Override
  public List<TaxDebtInfoDto> getTaxDebtInfo(BusinessFilterInfoOption inputOptionInfo) {
    switch (BusinessType.fromValue(inputOptionInfo.getTypeOption())) {
      case CUSTOM_DEPT:
        return customDebtRepository.getBusinessForCustomDebt(
            WebUtils.getOrgId(),
            inputOptionInfo.getBusinessId(),
            inputOptionInfo.getTop()
        );
      case BRANCH_DEPT:
        return taxBranchDebtRepository.getBusinessForTaxBranchDebt(
            WebUtils.getOrgId(),
            inputOptionInfo.getBusinessId(),
            inputOptionInfo.getTop()
        );
      default:
        return List.of();
    }
  }

  @Override
  public List<PostDto> getPostInfo(PostFilter filterPost) {
    return postRepositoryCustom.getBusinessForPost(
        WebUtils.getOrgId(),
        filterPost.getBusinessId(),
        filterPost.getTop(),
        filterPost.getOptionSort()
    );
  }
}
