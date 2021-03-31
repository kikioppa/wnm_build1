package com.example.dodgema.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.data.annotation.Transient;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")

    private String email;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "password")
    //@Length(min = 5, message = "*Your password must have at least 5 characters")

    private String password;
    @Column(name = "name")

    private String name;
    @Column(name = "last_name")

    private String lastName;
    @Column(name = "login_kind")
    private int loginKind;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdate;
    @Column(name = "active")
    private int active;

    public boolean isEnabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}