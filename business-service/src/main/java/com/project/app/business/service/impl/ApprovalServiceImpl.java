package com.project.app.business.service.impl;

import com.project.app.business.converter.PostConverter;
import com.project.app.business.domain.CustomDebt;
import com.project.app.business.domain.TaxBranchDebt;
import com.project.app.business.domain.TransDetailPost;
import com.project.app.business.domain.enums.BusinessType;
import com.project.app.business.dto.approval.ApprovalBusinessDto;
import com.project.app.business.dto.approval.ApprovalInfoDto;
import com.project.app.business.dto.approval.ApprovalPostDto;
import com.project.app.business.repository.CustomDebtRepository;
import com.project.app.business.repository.PostRepository;
import com.project.app.business.repository.TaxBranchDebtRepository;
import com.project.app.business.repository.TransDetailPostRepository;
import com.project.app.business.request.approval.ApprovalBusinessFilter;
import com.project.app.business.request.approval.ApprovalInfo;
import com.project.app.business.request.approval.ApprovalPostFilter;
import com.project.app.business.request.approval.ApprovalTypeInfo;
import com.project.app.business.service.ApprovalService;
import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.helper.DateTimeHelper;
import com.project.app.core.util.SecurityUtils;
import com.project.app.core.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {

  @Autowired
  private CustomDebtRepository customDebtRepository;

  @Autowired
  private TaxBranchDebtRepository taxBranchDebtRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private TransDetailPostRepository transDetailPostRepository;

  @Override
  public Page<ApprovalBusinessDto> filterApprovalBusinessTax(FilterPaging<ApprovalBusinessFilter> filterPaging) {
    switch (BusinessType.fromValue(filterPaging.getFilter().getTypeOption())) {
      case CUSTOM_DEPT:
        return customDebtRepository.filterApprovalBusinessForCustomDebt(
            WebUtils.getOrgId(),
            filterPaging.getFilter(),
            PageRequest.of(filterPaging.getPageIndex(), filterPaging.getPageSize())
        );
      case BRANCH_DEPT:
        return taxBranchDebtRepository.filterApprovalBusinessForTaxBranchDebt(
            WebUtils.getOrgId(),
            filterPaging.getFilter(),
            PageRequest.of(filterPaging.getPageIndex(), filterPaging.getPageSize())
        );
      default:
        return Page.empty();
    }
  }

  @Override
  public Page<ApprovalPostDto> filterApprovalPost(FilterPaging<ApprovalPostFilter> filterPaging) {
    return postRepository.filterApprovalBusinessForPost(
        WebUtils.getOrgId(),
        DateTimeHelper.toStartOfDay(filterPaging.getFilter().getStartDate()),
        DateTimeHelper.toEndOfDay(filterPaging.getFilter().getEndDate()),
        PostConverter.listSourceToString(filterPaging.getFilter().getSources()),
        // filterPaging.getFilter().getCompleted(),
        // filterPaging.getFilter().getSpam(),
        filterPaging.getFilter().getKeyword(),
        // filterPaging.getFilter().getDomains(),
        filterPaging.getFilter().getStatus(),
        PageRequest.of(
            filterPaging.getPageIndex(),
            filterPaging.getPageSize(),
            PostConverter.convertSort(filterPaging.getSort())
        )
    );
  }

  @Override
  public RestHttpPostResponse updateInfo(Integer id, ApprovalInfo input) {
    final String username = SecurityUtils.getUsername();

    switch (BusinessType.fromValue(input.getTypeOption())) {
      case CUSTOM_DEPT: {
        CustomDebt customDebt = customDebtRepository.getByCustomDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("CustomDebt not found with id: " + id));

        customDebt.setStatus(input.getStatus());
        customDebt.setRankingApproval(input.getRankingApproval());
        customDebt.setReasonChangeRank(input.getReason());
        customDebt.setApprover(username);
        customDebt.setAssociate(username);

        customDebtRepository.save(customDebt);
        break;
      }

      case BRANCH_DEPT: {
        TaxBranchDebt taxBranchDebt = taxBranchDebtRepository.getByTaxBranchDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("TaxBranchDebt not found with id: " + id));

        taxBranchDebt.setStatus(input.getStatus());
        taxBranchDebt.setRankingApproval(input.getRankingApproval());
        taxBranchDebt.setReasonChangeRank(input.getReason());
        taxBranchDebt.setApprover(username);
        taxBranchDebt.setAssociate(username);

        taxBranchDebtRepository.save(taxBranchDebt);
        break;
      }

      case POST: {
        TransDetailPost transDetailPost = transDetailPostRepository.getByTransDetailPostsId(id)
            .orElseThrow(() -> new IllegalArgumentException("TransDetailPost not found with id: " + id));

        transDetailPost.setStatus(input.getStatus());
        transDetailPost.setRankingApproval(input.getRankingApproval());
        transDetailPost.setReasonChangeRank(input.getReason());
        transDetailPost.setApprover(username);
        transDetailPost.setAssociate(username);

        transDetailPostRepository.save(transDetailPost);
        break;
      }

      default:
        throw new IllegalArgumentException("Invalid type option: " + input.getTypeOption());
    }

    return new RestHttpPostResponse(id.longValue());
  }


  @Override
  public RestHttpPostResponse updateStatus(Integer id, ApprovalTypeInfo input) {
    final String username = SecurityUtils.getUsername();

    switch (BusinessType.fromValue(input.getTypeOption())) {
      case CUSTOM_DEPT: {
        CustomDebt customDebt = customDebtRepository.getByCustomDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("CustomDebt not found with id: " + id));

        customDebt.setStatus(input.getStatus());
        customDebt.setApprover(username);
        customDebt.setAssociate(username);

        customDebtRepository.save(customDebt);
        break;
      }

      case BRANCH_DEPT: {
        TaxBranchDebt taxBranchDebt = taxBranchDebtRepository.getByTaxBranchDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("TaxBranchDebt not found with id: " + id));

        taxBranchDebt.setStatus(input.getStatus());
        taxBranchDebt.setApprover(username);
        taxBranchDebt.setAssociate(username);

        taxBranchDebtRepository.save(taxBranchDebt);
        break;
      }

      case POST: {
        TransDetailPost transDetailPost = transDetailPostRepository.getByTransDetailPostsId(id)
            .orElseThrow(() -> new IllegalArgumentException("TransDetailPost not found with id: " + id));

        transDetailPost.setStatus(input.getStatus());
        transDetailPost.setApprover(username);
        transDetailPost.setAssociate(username);

        transDetailPostRepository.save(transDetailPost);
        break;
      }

      default:
        throw new IllegalArgumentException("Invalid type option: " + input.getTypeOption());
    }

    return new RestHttpPostResponse(id.longValue());
  }

  @Override
  public ApprovalInfoDto getInfo(Integer id, ApprovalTypeInfo input) {
    switch (BusinessType.fromValue(input.getTypeOption())) {

      case CUSTOM_DEPT: {
        CustomDebt customDebt = customDebtRepository.getByCustomDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("CustomDebt not found with id: " + id));

        return ApprovalInfoDto.builder()
            .rankingApproval(customDebt.getRankingApproval())
            .reason(customDebt.getReasonChangeRank())
            .build();
      }

      case BRANCH_DEPT: {
        TaxBranchDebt taxBranchDebt = taxBranchDebtRepository.getByTaxBranchDebtId(id)
            .orElseThrow(() -> new IllegalArgumentException("TaxBranchDebt not found with id: " + id));

        return ApprovalInfoDto.builder()
            .rankingApproval(taxBranchDebt.getRankingApproval())
            .reason(taxBranchDebt.getReasonChangeRank())
            .build();
      }

      case POST: {
        TransDetailPost transDetailPost = transDetailPostRepository.getByTransDetailPostsId(id)
            .orElseThrow(() -> new IllegalArgumentException("TransDetailPost not found with id: " + id));

        return ApprovalInfoDto.builder()
            .rankingApproval(transDetailPost.getRankingApproval())
            .reason(transDetailPost.getReasonChangeRank())
            .build();
      }

      default:
        throw new IllegalArgumentException("Invalid type option: " + input.getTypeOption());
    }
  }

}
