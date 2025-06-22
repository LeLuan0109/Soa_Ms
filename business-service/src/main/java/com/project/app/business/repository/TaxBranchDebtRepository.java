package com.project.app.business.repository;

import com.project.app.business.domain.CustomDebt;
import com.project.app.business.domain.TaxBranchDebt;
import com.project.app.business.dto.approval.ApprovalBusinessDto;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import com.project.app.business.request.approval.ApprovalBusinessFilter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBranchDebtRepository extends JpaRepository<TaxBranchDebt, Integer> {

  @Query("select new com.project.app.business.dto.approval.ApprovalBusinessDto("
      + "cb.modifyTime,"
      + "cb.debtAmount,"
      + "null,"
      + "null,"
      + "cb.taxPeriod,"
      + "cb.rankingAuto,"
      + "cb.rankingApproval,"
      + "cb.warningLevel,"
      + "cb.taxBranchDebtId,"
      + "cb.business.businessCode,"
      + "cb.business.companyName,"
      + "cb.business.taxCode,"
      + "cb.business.stockCode,"
      + "cb.reasonChangeRank,"
      + "cb.status"
      + ") "
      + "from TaxBranchDebt cb "
      + "join Business b on b.businessId = cb.business.businessId "
      + "where :orgId = b.orgId "
      + "and (:#{#filter.status} is null or cb.status = :#{#filter.status}) "
  )
  Page<ApprovalBusinessDto> filterApprovalBusinessForTaxBranchDebt(
      @Param("orgId") Integer orgId,
      ApprovalBusinessFilter filter,
      Pageable pageable);

  @Query("select new com.project.app.business.dto.business.TaxDebtInfoDto("
      + "cb.modifyTime,"
      + "cb.debtAmount,"
      + "null,"
      + "null,"
      + "cb.taxPeriod,"
      + "cb.rankingAuto,"
      + "cb.rankingApproval,"
      + "cb.warningLevel"
      + ") "
      + "from TaxBranchDebt cb "
      + "join Business b on b.businessId = cb.business.businessId "
      + "where :orgId = b.orgId "
      + "and :businessId = b.businessId "
      + "and cb.status = 1 "
      + "order by cb.modifyTime desc "
      + "limit :top"
  )
  List<TaxDebtInfoDto> getBusinessForTaxBranchDebt(
      @Param("orgId") Integer orgId,
      @Param("businessId") Integer businessId,
      @Param("top") Integer top
  );

  Optional<TaxBranchDebt> getByTaxBranchDebtId(Integer id);

}
