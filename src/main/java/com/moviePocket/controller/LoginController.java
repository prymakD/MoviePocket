package com.moviePocket.controller;

import com.moviePocket.Service.EmailSenderService;
import com.moviePocket.Service.UserService;
import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }
    @Autowired
    private UserService userService;



    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserRegistrationDto userDto,
            BindingResult result,
            Model model) throws MessagingException {
        User existingUser = userService.findUserByEmail(userDto.getEmail());


        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
