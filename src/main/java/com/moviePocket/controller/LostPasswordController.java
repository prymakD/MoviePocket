package com.moviePocket.controller;

import com.moviePocket.Service.UserService;
import com.moviePocket.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/lostpassword")

public class LostPasswordController {

    @Autowired
    private UserService userService;
    @GetMapping("")
    public String registrationForm() {
        return "lost_pas";
    }

    @PostMapping("/")
    public String setMail(@RequestParam("username") String username) throws MessagingException {
        userService.setToken(username);
        return "lost_pas";
    }

    @GetMapping("/reset")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "new_pas";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam("token") String token,@RequestParam("password") String password) {
        System.out.println(token);
        System.out.println(userService.setNewPassword(token,password));
        return "login";
    }

}