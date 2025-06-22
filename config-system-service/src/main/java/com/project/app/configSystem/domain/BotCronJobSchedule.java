package com.project.app.configSystem.domain;

import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bot_cronjob_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotCronJobSchedule extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bot_cronjob_schedule_id")
  private Integer botCronjobScheduleId;

  @Column(name = "business_id")
  private Integer businessId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "status")
  private Integer status;

  @Column(name = "config_time")
  private String configTime;

  @Column(name = "update_by")
  private String updateBy;
}
