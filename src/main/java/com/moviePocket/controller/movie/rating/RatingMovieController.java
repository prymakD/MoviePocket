package com.moviePocket.controller.movie.rating;

import com.moviePocket.entities.movie.rating.Rating;
import com.moviePocket.service.movie.rating.RatingMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/rating")
@Api(value = "Rating Movie Controller", tags = "Controller to rate movies, avr rating is double value")
public class RatingMovieController {

    @Autowired
    RatingMovieService ratingMovieService;

    @PostMapping("/set")
    @ApiOperation(value = "Set the rating for a movie", notes = "Ff user already set rating for the movie it will be updated")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set the rating"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    public ResponseEntity<Void> setRatingMovie(
            @RequestParam("MovieId") Long MovieId,
            @RequestParam("rating") int rating) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.setNewRatingMovie(authentication.getName(), MovieId, rating);
    }

    @PostMapping("/del")
    @ApiOperation("Remove the rating for a movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully removed the rating"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    public ResponseEntity<Void> delRatingMovie(@RequestParam("MovieId") Long MovieId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.removeFromRatingMovie(authentication.getName(), MovieId);
    }

    @GetMapping("/get")
    @ApiOperation("Get the rating for a movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved the rating"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    public ResponseEntity<Integer> getRatingMovie(@RequestParam("MovieId") Long MovieId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getFromRatingMovie(authentication.getName(), MovieId);
    }

    @GetMapping("/allByUser")
    @ApiOperation("Get all ratings for a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved the users' ratings"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    public ResponseEntity<List<Rating>> allRatingByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ratingMovieService.getAllUserRatingMovie(authentication.getName());
    }

    @GetMapping("/rating")
    public ResponseEntity<Double> getMovieRating(@RequestParam("id") Long id) {
        return ratingMovieService.getMovieRating(id);
    }

    @GetMapping("/count/rating")
    public ResponseEntity<Integer> getCountMovieRating(@RequestParam("id") Long id) {
        return ratingMovieService.getAllCountByIdMovie(id);
    }

}