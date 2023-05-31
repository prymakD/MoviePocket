package com.moviePocket.controller.user;

import com.moviePocket.Service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/user/watched")
public class WatchedMovieController {

    @Autowired
    WatchedMovieService watchedMovieService;

    @PostMapping("/set")
    public boolean setMovieWatched(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.setNewWatched(authentication.getName(),id);
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

}
