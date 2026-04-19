package com.example.knowledgehub_library_management_system.common.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="genres")
@Setter
@Getter
public class Genre extends BaseEntity
{

    private String name;

}
