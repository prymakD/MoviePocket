package com.moviePocket.controller.user;

import com.moviePocket.entities.movie.rating.Rating;
import com.moviePocket.service.movie.rating.RatingMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/user/rating")
public class RatingMovieController {

    @Autowired
    RatingMovieService ratingMovieService;

    @PostMapping("/set")
    public ResponseEntity<?> setRatingMovie(@RequestParam("id") Long id, @RequestParam("rating") int rating) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ratingMovieService.setNewRatingMovie(authentication.getName(), id, rating);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/del")
    public ResponseEntity<?> delRatingMovie(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        ratingMovieService.removeFromRatingMovie(authentication.getName(), id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getRatingMovie(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int rating = ratingMovieService.getFromRatingMovie(authentication.getName(), id);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> allRatingMovie() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Rating> ratings = ratingMovieService.getAllUserRatingMovie(authentication.getName());
        return ResponseEntity.ok(ratings);
    }


}
