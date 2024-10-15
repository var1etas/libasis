package ru.filatov.libasis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column(name = "activity_status")
    private Boolean activityStatus;

    @Column
    private String description;

    public RoleEntity(Integer id, String name, Boolean activityStatus, String description) {
        this.id = id;
        this.name = name;
        this.activityStatus = activityStatus;
        this.description = description;
    }

    public RoleEntity(String name, Boolean activityStatus, String description) {
        this.name = name;
        this.activityStatus = activityStatus;
        this.description = description;
    }
    public RoleEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActivityStatus() {
        return activityStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String role) {
        this.name = role;
    }

    public void setActivityStatus(Boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + name + '\'' +
                ", activityStatus='" + activityStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
