package com.project.app.configSystem.repository;

import com.project.app.configSystem.domain.BotCronJobSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotCronJobScheduleRepository extends JpaRepository<BotCronJobSchedule, Integer> {
    // Custom query methods can be defined here if needed
    // For example, to find a schedule by its name:
    // Optional<BotCronJobSchedule> findByName(String name);

}
