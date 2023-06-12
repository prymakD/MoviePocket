package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.FavoriteMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/favorite")
public class FavoriteMovieController {

    @Autowired
    FavoriteMovieService favoriteMoviesService;

    @PostMapping("/set")
    public ResponseEntity<?> setOrDeleteFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        favoriteMoviesService.setOrDeleteNewFavoriteMovies(authentication.getName(), idMovie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getIsUserFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean isFavorite = favoriteMoviesService.getFromFavoriteMovies(authentication.getName(), idMovie);
        return ResponseEntity.ok(isFavorite);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> allUserFavoriteMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Long> favoriteMovies = favoriteMoviesService.getAllUserFavoriteMovies(authentication.getName());
        return ResponseEntity.ok(favoriteMovies);
    }


}