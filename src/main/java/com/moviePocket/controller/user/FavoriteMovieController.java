package com.moviePocket.controller.user;

import com.moviePocket.Service.FavoriteMovieService;
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
@RequestMapping("/user/favoritemovie")
public class FavoriteMovieController {

    @Autowired
    FavoriteMovieService favoriteMoviesService;

    @PostMapping("/set")
    public void setFavoriteMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        favoriteMoviesService.setNewFavoriteMovies(
                authentication.getName(),id);
    }
    @PostMapping("/del")
    public void delFavoriteMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        favoriteMoviesService.removeFromFavoriteMovies(authentication.getName(),id);
    }
    @GetMapping("/get")
    public boolean getFavoriteMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getFromFavoriteMovies(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<Long> allFavoriteMovie(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getAllUserFavoriteMovies(
                authentication.getName());
    }

}