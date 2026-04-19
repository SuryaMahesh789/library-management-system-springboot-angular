package com.example.knowledgehub_library_management_system.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role extends BaseEntity
{

    @Column(unique = true)
    private String name;

}
