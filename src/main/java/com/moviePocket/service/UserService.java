package com.moviePocket.service;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;

public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto registrationDto) throws MessagingException;

    User findById(Long id);

    User findUserByEmail(String email);

    boolean activateUser(String code);

    boolean setTokenPassword(String mail) throws MessagingException;

    boolean setNewLostPassword(String token,String pas);

    boolean setNewPassword(String email, String passwordOld,String passwordNew);

    boolean deleteUser(String email, String pas);

    boolean setTokenEmail(String oldEmail, String newEmail) throws MessagingException;

    ResponseEntity<Void> activateNewEmail(String token);

    ResponseEntity<Void> setNewUsername(String email, String username);

    ResponseEntity<Void> setNewBio(String email, String bio);

    User findUserByUsername(String username);

}
