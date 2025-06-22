package com.project.app.business.service.impl;

import com.project.app.business.domain.Business;
import com.project.app.business.dto.business.BusinessDto;
import com.project.app.business.repository.BusinessRepository;
import com.project.app.business.request.business.BusinessFilter;
import com.project.app.business.request.business.BusinessInfo;
import com.project.app.business.request.business.BusinessInfoStatus;
import com.project.app.business.service.BusinessService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.helper.DateTimeHelper;
import com.project.app.core.helper.GenericExcelHandler;
import com.project.app.core.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

  @Autowired
  private BusinessRepository businessRepository;

  @Autowired
  private GenericExcelHandler genericExcelHandler;

  @Override
  public Page<BusinessDto> filter(FilterPaging<BusinessFilter> filterPaging) {
    String infoBusiness = filterPaging.getFilter().getInfoBusiness();
    String businessCode = null;
    String companyName = null;
    boolean infoBusinessHasDash = infoBusiness != null && infoBusiness.contains("-");

    if (infoBusinessHasDash) {
      String[] parts = parseInfoBusiness(infoBusiness);
      businessCode = parts[0].trim();
      companyName = parts[1].trim();
    } else if (infoBusiness != null) {
      businessCode = infoBusiness.trim();
      companyName = infoBusiness.trim();
    }

    return businessRepository.filterBusiness(
        filterPaging.getFilter().getTaxCode(),
        filterPaging.getFilter().getStockCode(),
        filterPaging.getFilter().getRank(),
        businessCode,
        companyName,
        infoBusinessHasDash,
        PageRequest.of(filterPaging.getPageIndex(), filterPaging.getPageSize()));
  }

  @Override
  @Transactional
  public RestHttpPostResponse creat(BusinessInfo input) {
    return new RestHttpPostResponse(saveBusiness(input, WebUtils.getOrgId()).getBusinessId().longValue());
  }

  @Override
  @Transactional
  public RestHttpPostResponse update(Integer id, BusinessInfo input) {
    Integer orgId = WebUtils.getOrgId();
    Business existing = businessRepository.findByBusinessIdAndOrgId(id, orgId)
        .orElseThrow(() -> new IllegalArgumentException("Business not found for update."));

    String[] parts = parseInfoBusiness(input.getInfoBusiness());
    String businessCode = parts[0];
    String companyName = parts[1];

    boolean isDuplicate = businessRepository.existsByBusinessKeyFieldsExcludeId(
        id, orgId, businessCode, input.getTaxCode(), input.getStockCode(), input.getIdCard());

    if (isDuplicate) {
      throw new IllegalArgumentException("Business with the same information already exists in this organization.");
    }

    existing.setBusinessCode(businessCode);
    existing.setCompanyName(companyName);
    existing.setTaxCode(input.getTaxCode());
    existing.setStockCode(input.getStockCode());
    existing.setIdCard(input.getIdCard());
    existing.setForSearching(input.getForSearching());

    businessRepository.save(existing);
    return new RestHttpPostResponse(existing.getBusinessId().longValue());
  }

  @Override
  @Transactional
  public RestHttpPostResponse updateStatus(Integer id, BusinessInfoStatus inputStatus) {
    // TODO: Call API to update bot-cronjob with new status for given businessId and orgId
    return new RestHttpPostResponse();
  }

  @Override
  public BusinessDto getBusinessDetail(Integer id) {
    return businessRepository.getBusinessDetail(id, WebUtils.getOrgId())
        .orElseThrow(() -> new IllegalArgumentException("Business not found."));
  }

  @Override
  @Transactional
  public RestHttpPostResponse createFromExcelFile(MultipartFile file) throws Exception {
    List<BusinessInfo> businessInfos = genericExcelHandler.readExcelFile(file, BusinessInfo.class);
    log.info("Parsed BusinessInfo from Excel: {}", businessInfos);

    Integer orgId = WebUtils.getOrgId();

    for (BusinessInfo info : businessInfos) {
      Business saved = saveBusiness(info, orgId);
      if (saved.getBusinessId() == null) {
        throw new IllegalStateException("Failed to save business record from Excel.");
      }
    }

    return new RestHttpPostResponse();
  }

  // ========== Private Methods ==========

  @Transactional
  protected Business saveBusiness(BusinessInfo input, Integer orgId) {
    String[] parts = parseInfoBusiness(input.getInfoBusiness());
    String businessCode = parts[0];
    String companyName = parts[1];

    boolean isExists = businessRepository.existsByBusinessKeyFields(
        orgId, businessCode, input.getTaxCode(), input.getStockCode(), input.getIdCard());

    if (isExists) {
      throw new IllegalArgumentException("Business with the same information already exists in this organization.");
    }

    return businessRepository.save(Business.builder()
        .orgId(orgId)
        .codeCrawl(generateCodeCrawl(businessCode, orgId))
        .businessCode(businessCode)
        .companyName(companyName)
        .forSearching(input.getForSearching())
        .idCard(input.getIdCard())
        .taxCode(input.getTaxCode())
        .stockCode(input.getStockCode())
        .build());
  }

  private String[] parseInfoBusiness(String infoBusiness) {
    if (infoBusiness == null || !infoBusiness.contains("-")) {
      throw new IllegalArgumentException("Invalid infoBusiness format. Expected format: <businessCode> - <companyName>");
    }

    String[] parts = infoBusiness.split("-", 2);
    return new String[]{parts[0].trim(), parts[1].trim()};
  }

  private String generateCodeCrawl(String businessCode, Integer orgId) {
    return businessCode + "-" + orgId + "-" + DateTimeHelper.getCurrentTimestamp();
  }
}
