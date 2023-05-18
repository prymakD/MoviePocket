package com.moviePocket.controller;

import com.moviePocket.Service.UserService;
import com.moviePocket.entities.Role;
import com.moviePocket.entities.User;
import com.moviePocket.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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






}
