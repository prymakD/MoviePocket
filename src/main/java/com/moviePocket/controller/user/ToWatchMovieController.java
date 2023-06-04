package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.ToWatchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/towatch")
public class ToWatchMovieController {

    @Autowired
    ToWatchMovieService toWatchMovieService;

    @PostMapping("/set")
    public void setOrDeleteMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        toWatchMovieService.setOrDeleteToWatch(authentication.getName(), id);
    }

    @GetMapping("/get")
    public boolean getIsUserMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getFromToWatch(
                authentication.getName(), id);
    }

    @GetMapping("/all")
    public List<Long> allUserMovieToWatchMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getAllUserToWatch(
                authentication.getName());
    }


}
