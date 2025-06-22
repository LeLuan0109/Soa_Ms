package com.project.app.configSystem.repository;

import com.project.app.configSystem.domain.RuleBase;
import com.project.app.configSystem.dto.RuleBaseDto;
import com.project.app.configSystem.request.RuleBaseFilter;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleBaseRepository extends JpaRepository<RuleBase, Integer> {

  @Query("select count (rb) > 0 from RuleBase rb where " +
      "rb.orgId = :orgId and " +
      "rb.type = :type and " +
      "rb.ruleBaseName = :name "
      )
  boolean existsByRuleBaseKeyFields(Integer orgId, String type, String name);

  @Query("select count(rb) > 0 from RuleBase rb where " +
      "rb.ruleBaseId <> :id and " +
      "rb.orgId = :orgId and " +
      "rb.type = :type and " +
      "rb.ruleBaseName = :name")
  boolean existsByRuleBaseKeyFieldsExcludeId(@Param("id") Integer id,
      @Param("orgId") Integer orgId,
      @Param("type") String type,
      @Param("name") String name);


  @Query(
      "select new com.project.app.configSystem.dto.RuleBaseDto("
          + "rb.modifyTime,"
          + "rb.updator,"
          + "rb.ruleBaseId, "
          + "rb.ruleBaseName, "
          + "rb.rankingDefault, "
          + "rb.status"
          + ") "
          + "from RuleBase rb "
          + "where rb.orgId = :orgId "
          + "and (:#{#filter.status} is null or rb.status = :#{#filter.status}) "
          + "and (:#{#filter.code} is null or rb.type = :#{#filter.code}) "
          + "order by rb.modifyTime desc"
  )
  Page<RuleBaseDto> filterRuleBase(
      @Param("orgId") Integer orgId,
      RuleBaseFilter filter,
      Pageable pageable
  );

  @Query("select new com.project.app.configSystem.dto.RuleBaseDto(" +
      "rb.modifyTime, " +
      "rb.updator, " +
      "rb.ruleBaseId, " +
      "rb.ruleBaseName, " +
      "rb.rankingDefault, " +
      "rb.status) " +
      "from RuleBase rb " +
      "where rb.ruleBaseId = :id")
  Optional<RuleBaseDto> findRuleBaseDtoById(@Param("id") Integer id);

}
