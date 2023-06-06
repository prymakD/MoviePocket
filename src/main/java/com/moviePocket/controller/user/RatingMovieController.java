package com.moviePocket.controller.user;

import com.moviePocket.entities.movie.rating.Rating;
import com.moviePocket.service.movie.rating.RatingMovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void setRatingMovie(@RequestParam("id") Long id,@RequestParam("rating") int rating){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ratingMovieService.setNewRatingMovie(
                authentication.getName(),id,rating);
    }
    @PostMapping("/del")
    public void delRatingMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ratingMovieService.removeFromRatingMovie(authentication.getName(),id);
    }
    @GetMapping("/get")
    public int getRatingMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getFromRatingMovie(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<Rating> allRatingMovie(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getAllUserRatingMovie(
                authentication.getName());
    }

}
