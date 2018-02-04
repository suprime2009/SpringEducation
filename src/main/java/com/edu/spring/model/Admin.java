package com.edu.spring.model;

import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = "ADMIN")
public class Admin extends User {

    private static final long serialVersionUID = -5961965458744164963L;

    private static final List<Role> ROLES_TO_CHANGE = new ArrayList<>();

    private boolean isRoot;

    /**
     * Shows if admin is root. Root can create other admins.
     *
     * @return true if is root, false - else.
     */
    @Column(name = "is_root", nullable = false)
    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    @Override
    @Transient
    public List<Role> getAllowedRolesToMove() {
        return ROLES_TO_CHANGE;
    }

    @Override
    @Transient
    public Role getRoleEnum() {
        return Role.ADMIN;
    }
}
