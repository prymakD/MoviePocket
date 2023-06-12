package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.DislikedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/dislikedMovie")
public class DislikedMovieController {

    @Autowired
    DislikedMovieService dislikedMovieService;

    @PostMapping("/set")
    public ResponseEntity<?> setOrDeleteMovieWatched(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        System.out.println(authentication.getName());
        dislikedMovieService.setOrDeleteDislikedMovie(authentication.getName(), idMovie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getIsUserDislikedMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean isDisliked = dislikedMovieService.getFromDislikedMovie(authentication.getName(), idMovie);
        return ResponseEntity.ok(isDisliked);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> allUserDislikedMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Long> dislikedMovies = dislikedMovieService.getAllUserDislikedMovie(authentication.getName());
        return ResponseEntity.ok(dislikedMovies);
    }

}