package com.project.app.business.repository;

import com.project.app.business.domain.TaxStatus;
import com.project.app.business.dto.business.BusinessStatusInfoDto;
import com.project.app.business.dto.business.TaxDebtInfoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxStatusRepository  extends JpaRepository<TaxStatus, Integer> {

  @Query("select new com.project.app.business.dto.business.BusinessStatusInfoDto("
      + "cb.modifyTime,"
      + "cb.taxStatus,"
      + "cb.rankingApproval,"
      + "cb.warningLevel"
      + ") "
      + "from TaxStatus cb "
      + "join Business b on b.businessId = cb.business.businessId "
      + "where :orgId = b.orgId "
      + "and :businessId = b.businessId "
      + "and cb.status = 1 "
      + "order by cb.modifyTime desc "
      + "limit :top"
  )
  List<BusinessStatusInfoDto> getBusinessForTaxStatus(
      @Param("orgId") Integer orgId,
      @Param("businessId") Integer businessId,
      @Param("top") Integer top
  );

}
