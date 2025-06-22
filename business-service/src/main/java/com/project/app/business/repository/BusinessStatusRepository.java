package com.project.app.business.repository;

import com.project.app.business.domain.BusinessStatus;
import com.project.app.business.dto.business.BusinessStatusInfoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessStatusRepository extends JpaRepository<BusinessStatus, Integer> {

  @Query("select new com.project.app.business.dto.business.BusinessStatusInfoDto("
      + "cb.modifyTime,"
      + "cb.businessStatus,"
      + "cb.rankingApproval,"
      + "cb.warningLevel"
      + ") "
      + "from BusinessStatus cb "
      + "join Business b on b.businessId = cb.business.businessId "
      + "where :orgId = b.orgId "
      + "and :businessId = b.businessId "
      + "and cb.status = 1 "
      + "order by cb.modifyTime desc "
      + "limit :top"
  )
  List<BusinessStatusInfoDto> getBusinessForBusinessStatus(
      @Param("orgId") Integer orgId,
      @Param("businessId") Integer businessId,
      @Param("top") Integer top
  );

}