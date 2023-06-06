package com.moviePocket.controller.user;

import com.moviePocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@Controller
@RequestMapping("/user/edit")
public class UserEditController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String editForm() {
        return "user_edit";
    }


    @GetMapping("/delete")
    public String deleteForm() {
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("password") String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userService.deleteUser(authentication.getName(),password))
            return "login";
        else
            return "delete";
    }


    @GetMapping("/newpas")
    public String newPasswordGetForm() {
        return "set_new_pas";
    }

    @PostMapping("/newpas")
    public String newPasswordPostForm(@RequestParam("passwordold") String passwordOld,
                                      @RequestParam("password0") String passwordNew0,
                                      @RequestParam("password1") String passwordNew1) {
        if(passwordNew0.equals(passwordNew1) && !passwordOld.equals(passwordNew1)){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userService.setNewPassword(authentication.getName(),passwordOld,passwordNew0);
            return "user_edit";
        }

        return "set_new_pas";
    }

    @PostMapping("/newemail")
    public String newEmailGetForm(@RequestParam("email") String email) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setTokenEmail(authentication.getName(),email);
        return "user_edit";
    }

    @GetMapping("/newemail/{token}")
    public String activate(@PathVariable String token) {
        userService.activateNewEmail(token);
        return "user_edit";
    }


    @PostMapping("/newusername")
    public String newSetNewUsername(@RequestParam("username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setNewUsername(authentication.getName(),username);
        return "user_edit";
    }


    @PostMapping("/newbio")
    public String newSetNewBio(@RequestParam("bio") String bio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setNewBio(authentication.getName(),bio);
        return "user_edit";
    }





}
