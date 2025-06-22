package com.project.app.business.service;


import com.project.app.business.dto.business.BusinessDto;
import com.project.app.business.request.business.BusinessInfo;
import com.project.app.business.request.business.BusinessFilter;
import com.project.app.business.request.business.BusinessInfoStatus;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessService{

  Page<BusinessDto> filter(FilterPaging<BusinessFilter> filterPaging);

  RestHttpPostResponse creat(BusinessInfo input);

  RestHttpPostResponse update(Integer id, BusinessInfo input);

  RestHttpPostResponse updateStatus(Integer id, BusinessInfoStatus inputStatus);

  BusinessDto getBusinessDetail(Integer id);

  RestHttpPostResponse createFromExcelFile(MultipartFile file) throws Exception;
}
