package com.moviePocket.controller;

import com.moviePocket.util.Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = Utils.CORS_HOST)
@RestController
public class MyController {

    @GetMapping("/printCookies")
    public void printCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Name: " + cookie.getName() + ", Value: " + cookie.getValue());
            }
        }
    }
}
