package ru.filatov.libasis.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String role;

    @Column(name = "activity_status")
    private String activityStatus;

    @Column
    private String description;
}
