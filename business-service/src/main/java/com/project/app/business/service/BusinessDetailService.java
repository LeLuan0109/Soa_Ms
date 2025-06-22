package com.project.app.business.service;

import com.project.app.business.dto.business.BusinessStatusInfoDto;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import com.project.app.business.dto.post.PostDto;
import com.project.app.business.request.business.BusinessFilterInfoOption;
import com.project.app.business.request.post.PostFilter;
import java.util.List;

public interface BusinessDetailService {
  /**
   * Retrieves the business status and tax status of the business based on the provided input options (business status | tax status).
   *
   * @param inputOptionInfo the input option (business | tax ), top(quantity you want to get) containing the other option
   * @return a list of status information
   */
  List<BusinessStatusInfoDto> getBusinessStatusInfo(BusinessFilterInfoOption inputOptionInfo);

  /**
   * Retrieves a list of branch tax debts or customs tax debts based on the provided input options.
   *
   * @param inputOptionInfo input options (branch | customs), top(quantity you want to get) containing other options
   * @return list of information
   */
  List<TaxDebtInfoDto> getTaxDebtInfo(BusinessFilterInfoOption inputOptionInfo);

  List<PostDto> getPostInfo(PostFilter filterPost);
}
