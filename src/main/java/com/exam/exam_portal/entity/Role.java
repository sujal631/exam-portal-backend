package com.exam.exam_portal.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Long roleID;
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRole> userRoles = new ArrayList<>();

    public Role() {
    }

    public Role(Long roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role [roleID=" + roleID + ", roleName=" + roleName + "]";
    }

}
