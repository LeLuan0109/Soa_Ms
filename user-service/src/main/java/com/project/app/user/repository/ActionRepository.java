package com.project.app.user.repository;

import com.project.app.user.domain.Action;
import com.project.app.user.dto.role.ActionDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionRepository extends JpaRepository<Action , Integer> {

  @Query(
      "select new com.project.app.user.dto.role.ActionDto("
          + "a.code,"
          + "a.name)"
          + "from Action as a"
  )
  List<ActionDto> getAllAction();
}
