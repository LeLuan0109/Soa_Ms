package com.project.app.configSystem.repository;

import com.project.app.configSystem.domain.NegativeKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegativeKeywordRepository extends JpaRepository<NegativeKeyword, Integer> {

}
