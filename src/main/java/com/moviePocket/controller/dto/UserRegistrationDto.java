package com.moviePocket.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moviePocket.entities.user.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDto {
    private Long id;

    @NotEmpty(message = "Please enter valid username.")
    private String username;

    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    //TODO password validation for registrasion

    //    @ValidPassword
    private String password;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);

        return user;
    }

    public static UserRegistrationDto fromUser(User user) {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
