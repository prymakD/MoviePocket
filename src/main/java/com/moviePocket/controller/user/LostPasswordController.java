package com.moviePocket.controller.user;

import com.moviePocket.security.validation.ValidPassword;
import com.moviePocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
@RestController
@RequestMapping("/lostpassword")
//@Validated
//TODO password validation for lost password
public class LostPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String registrationForm() {
        return "lost_pas";
    }

    @PostMapping("/")
    public String setMail(@RequestParam("username") String username) throws MessagingException {
        userService.setTokenPassword(username);
        return "lost_pas";
    }

    @GetMapping("/reset")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "new_pas";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password0") @ValidPassword String password0, @RequestParam("password1") String password1) {
        if (password0.equals(password1)) {
            userService.setNewLostPassword(token, password0);
            return "login";
        }
        return "new_pas";
    }

}