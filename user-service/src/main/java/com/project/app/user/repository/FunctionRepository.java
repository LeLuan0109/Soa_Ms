package com.project.app.user.repository;

import com.project.app.user.domain.Function;
import com.project.app.user.dto.role.FunctionDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FunctionRepository extends JpaRepository<Function , Integer> {

  @Query(
      "select new com.project.app.user.dto.role.FunctionDto("
          + "f.code,"
          + "f.label,"
          + "f.icon,"
          + "f.sort,"
          + "f.routerLink,"
          + "f.parentCode,"
          + "f.position,"
          + "f.status,"
          + "f.actions,"
          + "f.queryParams"
          + ") "
          + "FROM Function as f "
          + "where f.status = 1 "
          + "order by f.position, f.sort asc "
  )
  List<FunctionDto> getAllActionFunction();
}
