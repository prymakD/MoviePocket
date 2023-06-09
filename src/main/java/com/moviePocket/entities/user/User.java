package com.moviePocket.entities.user;


import com.moviePocket.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Lob
    private String bio;

    @Column(nullable = false)
    private Boolean emailVerification;

    @Column(nullable = true)
    private String activationCode;

    @Column(nullable = false)
    private boolean accountActive;

    @Column(nullable = true)
    private String newEmail;

    @Column(nullable = true)
    private String tokenLostPassword;

    @Column(nullable = true)
    private String newEmailToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )


    private List<Role> roles = new ArrayList<>();


    public User(String username, String email, String password,  List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;

    }

    public User(String username, String email, String password,  List<Role> roles, String activationCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.activationCode = activationCode;
        this.emailVerification = false;
        this.accountActive = true;
    }
}
