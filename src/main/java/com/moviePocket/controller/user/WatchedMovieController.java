package com.moviePocket.controller.user;

import com.moviePocket.Service.WatchedMovieService;
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
@RequestMapping("/user/watched")
public class WatchedMovieController {

    @Autowired
    WatchedMovieService watchedMovieService;

    @PostMapping("/set")
    public void setMovieWatched(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        watchedMovieService.setNewWatched(authentication.getName(),id);
    }

    @PostMapping("/del")
    public void delMovieWatched(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        watchedMovieService.removeFromWatched(authentication.getName(),id);
    }

    @GetMapping("/get")
    public boolean getMovieWatched(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getFromWatched(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<Long> allMovieWatched(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getAllUserWatched(
                authentication.getName());
    }

    @GetMapping("/")
    public String s(){
        return "ok";
    }

}
