package com.project.app.user.repository;

import com.project.app.user.domain.UserOrg;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserOrgRepository  extends JpaRepository<UserOrg, Integer> {

  boolean existsByUserIdAndOrgId(Integer userId, Integer orgId);

  @Query("SELECT au.orgId FROM UserOrg  as au WHERE au.userId = :userId ")
  List<Integer> getOrgIdByUserId(
      @Param("userId") Integer userId
  );

  @Modifying
  @Transactional
  @Query("DELETE FROM UserOrg au WHERE au.userId = :userId")
  void deleteByAuthorId(@Param("userId") Integer userId);
}
