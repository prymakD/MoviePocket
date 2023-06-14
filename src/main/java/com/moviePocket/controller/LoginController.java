package com.moviePocket.controller;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.user.User;
import com.moviePocket.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Api(value = "Login and Registration Controller", description = "Controller for user login/registration")
public class LoginController {

    private final UserService userService;
    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @ApiOperation(value = "Register a user ", notes = "Registration with username, password(with validation) and email, email and username should be unique, cookie based")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "User already registered"),
            @ApiResponse(code = 400, message = "Password does not match the criteria")
    })
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

    @ApiOperation(value = "Activate a user by email", notes = "Link is sent to the email after registration, returns whether user is activated(confirmed his/her mail or not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User is activated"),
            @ApiResponse(code = 409, message = "User activation code is not found"),
    })
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
