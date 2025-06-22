package com.project.app.user.repository;

import com.project.app.user.domain.User;
import com.project.app.user.dto.account.AccountDto;
import com.project.app.user.dto.authUser.AuthUserDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
  Optional<User> findByEmail(String email);

  @Query(
      "select new com.project.app.user.dto.account.AccountDto("
          + "a.id, "
          + "a.fullname, "
          + "a.username, "
          + "a.email, "
          + "a.phone, "
          + "coalesce(a.isActive, false), "
          +  "a.createTime "
          + ") "
          + "from User a "
          + "where 1 = 1 "
          + "and (:username is null or a.username like :username) "
          + "and (:fullName is null or a.fullname like :fullName )"
          + "and (cast(:created as date) is null or cast(a.createTime as date ) = :created) "
          + "and (:is_active is null or a.isActive = :is_active) "
          + "and (:id is null or a.id = :id)"
  )
  Page<AccountDto> filterAccount(
      @Param("username") String username,
      @Param("fullName") String fullName,
      @Param("created") LocalDate created,
      @Param("is_active") Boolean isActive,
      @Param("id") Integer id,
      Pageable pageable
  );

  @Query(
      "select r.roles "
          + "from Role as r "
          + "join RoleUser as ru on r.id = ru.roleId "
          + "where (:userId is null or ru.userId = :userId)"
  )
  String getRoleByUserId(
      @Param("userId") Integer userId
  );
  @Query(
      "select r.code "
          + "from Role as r "
          + "join RoleUser as ru on r.id = ru.roleId "
          + "where (:userId is null or ru.userId = :userId)"
  )
  String getIsAdminByUserId(
      @Param("userId") Integer userId
  );

  @Query(
      "select new com.project.app.user.dto.authUser.AuthUserDto("
          + "a.id,"
          + "r.roles, "
          + "a.username, "
          + "a.password, "
          + "a.isActive, "
          + "a.fullname, "
          + "a.userVersion, "
          +  "a.email,"
          + "a.phone,"
          + "a.birthday,"
          + "a.address,"
          + "r.code "
          + ") "
          + "from User a "
          + "join RoleUser as ru on ru.userId = a.id "
          + "join Role as r on r.id = ru.roleId "
          + "where 1 = 1 "
          + "and (:username is null or a.username like :username) "
  )
  AuthUserDto getUserByUsername(@Param("username") String username);

  @Query(
      "select new com.project.app.user.dto.authUser.AuthUserDto("
          + "a.id,"
          + "r.roles, "
          + "a.username, "
          + "a.password, "
          + "a.isActive, "
          + "a.fullname, "
          + "a.userVersion, "
          +  "a.email,"
          + "a.phone,"
          + "a.birthday,"
          + "a.address,"
          + "r.code "
          + ") "
          + "from User a "
          + "join RoleUser as ru on ru.userId = a.id "
          + "join Role as r on r.id = ru.roleId "
          + "where 1 = 1 "
          + "and (:username is null or a.username like :username) "
  )
  AuthUserDto getUserByEmail(@Param("username") String username);

  @Query(
      "select new com.project.app.user.dto.authUser.AuthUserDto("
          + "a.id,"
          + "r.roles, "
          + "a.username, "
          + "a.password, "
          + "a.isActive, "
          + "a.fullname, "
          + "a.userVersion, "
          +  "a.email,"
          + "a.phone,"
          + "a.birthday,"
          + "a.address,"
          + "r.code "
          + ") "
          + "from User a "
          + "join RoleUser as ru on ru.userId = a.id "
          + "join Role as r on r.id = ru.roleId "
          + "where 1 = 1 "
          + "and r.code = 'admin' "
  )
  List<AuthUserDto> getAllUserAdmin();
}
