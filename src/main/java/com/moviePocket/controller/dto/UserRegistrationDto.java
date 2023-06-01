package com.moviePocket.controller.dto;

import com.moviePocket.security.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private Long id;

    @NotEmpty(message = "Please enter valid username.")
    private String username;

    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    @ValidPassword
    private String password;
}
