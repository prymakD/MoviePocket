package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.ToWatchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> setOrDeleteMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        toWatchMovieService.setOrDeleteToWatch(authentication.getName(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getIsUserMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean isToWatch = toWatchMovieService.getFromToWatch(authentication.getName(), id);
        return ResponseEntity.ok(isToWatch);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> allUserMovieToWatchMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Long> toWatchMovies = toWatchMovieService.getAllUserToWatch(authentication.getName());
        return ResponseEntity.ok(toWatchMovies);
    }


}
