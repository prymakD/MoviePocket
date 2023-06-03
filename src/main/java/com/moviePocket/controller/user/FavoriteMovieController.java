package com.moviePocket.controller.user;

import com.moviePocket.service.FavoriteMovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void setOrDeleteFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        favoriteMoviesService.setOrDeleteNewFavoriteMovies(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    public boolean getIsUserFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getFromFavoriteMovies(
                authentication.getName(), idMovie);
    }

    @GetMapping("/all")
    public List<Long> allUserFavoriteMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getAllUserFavoriteMovies(
                authentication.getName());
    }

}