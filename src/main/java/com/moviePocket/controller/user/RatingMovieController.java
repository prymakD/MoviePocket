package com.moviePocket.controller.user;

import com.moviePocket.Service.DislikedMovieService;
import com.moviePocket.Service.RatingMovieService;
import com.moviePocket.entities.movie.RatingMovie;
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
@RequestMapping("/user/rating")
public class RatingMovieController {

    @Autowired
    RatingMovieService ratingMovieService;
    @PostMapping("/set")
    public void setDislikedMovie(@RequestParam("id") Long id,@RequestParam("id") int rating){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ratingMovieService.setNewRatingMovie(
                authentication.getName(),id,rating);
    }
    @PostMapping("/del")
    public void delDislikedMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ratingMovieService.removeFromRatingMovie(authentication.getName(),id);
    }
    @GetMapping("/get")
    public float getDislikedMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getFromRatingMovie(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<RatingMovie> allDislikedMovie(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getAllUserRatingMovie(
                authentication.getName());
    }

}
