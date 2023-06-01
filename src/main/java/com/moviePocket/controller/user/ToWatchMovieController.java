package com.moviePocket.controller.user;

import com.moviePocket.Service.ToWatchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user/towatch")
public class ToWatchMovieController {

    @Autowired
    ToWatchMovieService toWatchMovieService;
    @PostMapping("/set")
    public void setMovieToWatch(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        toWatchMovieService.setNewToWatch(authentication.getName(),id);
    }
    @PostMapping("/del")
    public void delMovieToWatch(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        toWatchMovieService.removeFromToWatch(authentication.getName(),id);
    }
    @GetMapping("/get")
    public boolean getMovieToWatch(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getFromToWatch(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<Long> allMovieToWatch(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getAllUserToWatch(
                authentication.getName());
    }


}
