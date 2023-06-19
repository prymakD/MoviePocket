package com.moviePocket.controller.user;

import com.moviePocket.security.validation.ValidPassword;
import com.moviePocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/lostpassword")

//@Validated
//TODO password validation for lost password
public class LostPasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("/setEmail")
    public ResponseEntity<Void> setMail(@RequestParam("email") String email) throws MessagingException {
        return userService.setTokenPassword(email);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestParam("token") String token, @RequestParam("password0") @ValidPassword String password0, @RequestParam("password1") String password1) {
        return userService.setNewLostPassword(token, password0, password1);
    }

}