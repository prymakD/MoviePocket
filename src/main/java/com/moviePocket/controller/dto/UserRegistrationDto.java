package com.moviePocket.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;

    public UserRegistrationDto(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
