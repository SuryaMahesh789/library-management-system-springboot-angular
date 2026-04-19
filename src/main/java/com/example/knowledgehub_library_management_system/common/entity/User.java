package com.example.knowledgehub_library_management_system.common.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="users")
@Getter
@Setter
public class User extends BaseEntity
{
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean isVerified;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

}
