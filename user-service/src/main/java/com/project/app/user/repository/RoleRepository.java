package com.project.app.user.repository;

import com.project.app.user.domain.Role;
import com.project.app.user.dto.role.RoleDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
  @Query("SELECT COUNT(r) > 0 FROM Role r WHERE r.name = :name")
  boolean existsByName(String name);

  @Query( value = "select new com.project.app.user.dto.role.RoleDto("
      + "r.createTime,"
      + "r.creator,"
      + "r.roles ,"
      + "r.code ,"
      + "r.name ,"
      + "r.id "
      + ") from Role r"
  )
  List<RoleDto> getAllRoles();

  @Query( value = "select new com.project.app.user.dto.role.RoleDto("
      + "r.createTime,"
      + "r.creator,"
      + "r.roles ,"
      + "r.code ,"
      + "r.name ,"
      + "r.id "
      + ") from Role r "
      + "where (:name is null or r.name like %:name%) "
      + "and (cast(:created as date) is null or cast(r.createTime as date ) = :created) "
      + "and (:creator is null or r.creator like %:creator%)"
  )
  Page<RoleDto> filterRoles(
      @Param("name") String name,
      @Param("created") LocalDate created,
      @Param("creator") String creator,
      Pageable pageable
  );

  @Query( value = "select new com.project.app.user.dto.role.RoleDto("
      + "r.createTime,"
      + "r.creator,"
      + "r.roles ,"
      + "r.code ,"
      + "r.name ,"
      + "r.id "
      + ") from Role r "
      + "where (:id is null or r.id = :id)"
  )
  Optional<RoleDto> getRoleDetail(
      @Param("id") Integer id
  );
}
