package ru.filatov.libasis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @Column
    private String name;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    public UserEntity() {
        super();
    }

    public UserEntity(RoleEntity role, String name, String email, String phoneNumber) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserEntity(Integer id, RoleEntity role, String name, String email, String phoneNumber) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public RoleEntity getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
