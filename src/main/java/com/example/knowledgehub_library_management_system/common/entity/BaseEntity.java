package com.example.knowledgehub_library_management_system.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate()
    {
        this.updatedAt = LocalDateTime.now();
    }

}
