package com.edu.spring.model;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "USER")
public abstract class User implements Serializable {

    private static final long serialVersionUID = -1724874381557103130L;

    private String uuid;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private boolean active = Boolean.TRUE;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String role;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "uuid", nullable = false, unique = true)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "mobile", unique = true, nullable = false)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "is_active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @CreatedDate
    @Column(name = "create_date")
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @LastModifiedDate
    @Column(name = "modified_date")
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Column(name = "role", insertable = false, updatable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    public abstract Role getRoleEnum();

    @Transient
    public abstract List<Role> getAllowedRolesToMove();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) && Objects.equals(email, user.email) && Objects.equals(mobile, user.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, mobile);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("uuid", uuid).append("name", name).append("email", email).append("mobile", mobile)
                .append("active", active).append("createDate", createDate).append("modifiedDate", modifiedDate).append("role", role).toString();
    }
}
