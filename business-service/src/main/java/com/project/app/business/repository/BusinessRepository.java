package com.project.app.business.repository;

import com.project.app.business.domain.Business;
import com.project.app.business.dto.business.BusinessDto;
import com.project.app.business.request.business.BusinessFilter;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessRepository extends JpaRepository<Business, Integer> {

  @Query("select count (b) > 0 from Business b where " +
      "b.orgId = :orgId and " +
      "b.businessCode = :businessCode and " +
      "b.taxCode = :taxCode and " +
      "b.stockCode = :stockCode and " +
      "b.idCard = :idCard")
  boolean existsByBusinessKeyFields(Integer orgId, String businessCode, String taxCode, String stockCode, String idCard);

  Optional<Business> findByBusinessIdAndOrgId(Integer id, Integer orgId);

  @Query("select count(b) > 0 from Business b where " +
      "b.businessId <> :id and " +
      "b.orgId = :orgId and " +
      "b.businessCode = :businessCode and " +
      "b.taxCode = :taxCode and " +
      "b.stockCode = :stockCode and " +
      "b.idCard = :idCard")
  boolean existsByBusinessKeyFieldsExcludeId(Integer id, Integer orgId, String businessCode, String taxCode, String stockCode, String idCard);

  @Query("select new com.project.app.business.dto.business.BusinessDto(" +
      "b.businessId, " +
      "b.businessCode, " +
      "b.companyName, " +
      "b.taxCode, " +
      "b.stockCode, " +
      "tx.taxStatus, " +
      "1, " +
      "tx.rankingAuto, " +
      "b.idCard, " +
      "b.forSearching) " +
      "from Business b " +
      "left join TaxStatus tx on b.businessId = tx.business.businessId " +
      "where b.businessId = :id and b.orgId = :orgId and (" +
      "tx.modifyTime = (" +
      "   select max(tx2.modifyTime) " +
      "   from TaxStatus tx2 " +
      "   where tx2.business.businessId = b.businessId" +
      ") or tx.modifyTime is null)")
  Optional<BusinessDto> getBusinessDetail(@Param("id") Integer id, @Param("orgId") Integer orgId);

  @Query("select new com.project.app.business.dto.business.BusinessDto(" +
      "b.businessId, " +
      "b.businessCode, " +
      "b.companyName, " +
      "b.taxCode, " +
      "b.stockCode, " +
      "tx.taxStatus, " +
      "1, " +  // Default is 1 (meaning activity)
      "tx.rankingAuto, " +
      "coalesce(tx.modifyTime, b.modifyTime)) " +
      "from Business b " +
      "left join TaxStatus tx on b.businessId = tx.business.businessId " +
      "where (tx.modifyTime = (" +
      "   select max(tx2.modifyTime) " +
      "   from TaxStatus tx2 " +
      "   where tx2.business.businessId = b.businessId" +
      ") or tx.modifyTime is null) " + // <- wrap the OR condition properly
      " and ( :taxCode is null or b.taxCode LIKE %:taxCode% ) "
      + " and ( :stockCode is null or b.stockCode LIKE %:stockCode% )"
      + " and ( :rank is null or tx.rankingAuto = :rank ) "
    + "and ( " +
  "   (:infoBusinessHasDash = false and " +
  "       ((:businessCode is null or b.businessCode like %:businessCode%) " +
  "        or (:companyName is null or b.companyName like %:companyName%)) " +
  "   ) or " +
  "   (:infoBusinessHasDash = true and " +
  "       ((:businessCode is null or b.businessCode like %:businessCode%) " +
  "        and (:companyName is null or b.companyName like %:companyName%)) " +
  "   ) " +
  ")"
  )
  Page<BusinessDto> filterBusiness(
      @Param("taxCode") String taxCode,
      @Param("stockCode") String stockCode,
      @Param("rank") Integer rank,
      @Param("businessCode") String businessCode,
      @Param("companyName") String companyName,
      @Param("infoBusinessHasDash") Boolean infoBusinessHasDash,
      Pageable pageable
  );

}
