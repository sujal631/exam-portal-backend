package com.exam.exam_portal.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    @Column(unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isEnable = true;
    private String imageUrl;

    // one user can have many roles
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    public User() {
    }

    public long getUserID() {
        return userID;
    }

    public User(long userID, String userName, String firstName, String lastName, String phoneNumber, String email,
            String password, boolean isEnable, String imageUrl) {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isEnable = isEnable;
        this.imageUrl = imageUrl;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
                + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password
                + ", isEnable=" + isEnable + ", imageUrl=" + imageUrl + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> list = new ArrayList<>();
        this.userRoles.forEach(userRole -> {
            list.add(new Authority(userRole.getRole().getRoleName()));
        });

        return list;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
