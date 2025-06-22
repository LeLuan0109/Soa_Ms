package com.project.app.configSystem.repository;

import com.project.app.configSystem.domain.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a source by its name:
    // Optional<Source> findByName(String name);

}
