package com.project.app.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class BaseEntity {
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;
    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
        this.modifyTime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.modifyTime = LocalDateTime.now();
    }

}
