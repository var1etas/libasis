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

    public RoleEntity(Integer id, String role, String activityStatus, String description) {
        this.id = id;
        this.role = role;
        this.activityStatus = activityStatus;
        this.description = description;
    }

    public RoleEntity(String role, String activityStatus, String description) {
        this.role = role;
        this.activityStatus = activityStatus;
        this.description = description;
    }
    public RoleEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", activityStatus='" + activityStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
