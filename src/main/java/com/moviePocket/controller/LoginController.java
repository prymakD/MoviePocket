package com.moviePocket.controller;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.user.User;
import com.moviePocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

//    private final AuthenticationManager authenticationManager;

    /*@PostMapping("/login")
    public String login(@RequestParam("username") String email,
                                   @RequestParam("password") String password, Model model) {
        try {
            // create an authentication token using the provided email and password
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

            // authenticate the user
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // redirect to the default success URL
            return "redirect:/user/";
        } catch (AuthenticationException e) {
            // add an error message to the model if authentication fails
            model.addAttribute("error", true);
            return "login";
        }
    }*/

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
