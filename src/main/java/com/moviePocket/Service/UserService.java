package com.moviePocket.Service;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;

public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto registrationDto) throws MessagingException;

    User findUserByEmail(String email);

    boolean activateUser(String code);

    boolean setTokenPassword(String mail) throws MessagingException;

    boolean setNewLostPassword(String token,String pas);

    boolean setNewPassword(String email, String passwordOld,String passwordNew);

    boolean deleteUser(String email,String pas);
    boolean setTokenEmail(String oldEmail, String newEmail) throws MessagingException;

    boolean activateNewEmail(String token);

    boolean setNewUsername(String email, String username);

    boolean setNewBio(String email, String bio);



}
