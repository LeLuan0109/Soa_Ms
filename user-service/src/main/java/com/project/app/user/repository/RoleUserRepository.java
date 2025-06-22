package com.project.app.user.repository;

import com.project.app.user.domain.RoleUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser , Integer> {

  Optional<RoleUser> findRoleUserByUserId(Integer userId);
}
