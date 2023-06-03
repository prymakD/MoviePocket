package com.moviePocket.controller.user;

import com.moviePocket.service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/watched")
public class WatchedMovieController {

    @Autowired
    WatchedMovieService watchedMovieService;

    @PostMapping("/set")
    public void setOrDeleteMovieWatched(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        watchedMovieService.setOrDeleteNewWatched(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    public boolean getIsMovieWatchedByUser(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getFromWatched(
                authentication.getName(), idMovie);
    }

    @GetMapping("/all")
    public List<Long> allUserMovieWatchedMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getAllUserWatched(
                authentication.getName());
    }

}
