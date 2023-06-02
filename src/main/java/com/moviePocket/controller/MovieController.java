package com.moviePocket.controller;

import com.moviePocket.controller.dto.MovieDto;
import com.moviePocket.service.*;
import com.moviePocket.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private RatingMovieService ratingMovieService;
    @Autowired
    private DislikedMovieService dislikedMovieService;
    @Autowired
    private FavoriteMovieService favoriteMovieService;
    @Autowired
    private WatchedMovieService watchedMovieService;
    @Autowired
    private ToWatchMovieService toWatchMovieService;

    @GetMapping("/{movieId}")
    public MovieDto getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/search/{query}")
    public List<MovieDto> getAllSearchedMovies(@PathVariable String query) {
        return movieService.searchMovie(query);
    }

    @GetMapping("/rating")
    public String getMovieRating(@RequestParam("id") String id){
        return ratingMovieService.getAllMovieRating(Long.valueOf(id));
    }
    @GetMapping("/count/rating")
    public String getCountMovieRating(@RequestParam("id") String id){
        return ratingMovieService.getAllCountByIdMovie(Long.valueOf(id));
    }
    @GetMapping("/count/dislike")
    public String getAllCountDislikedByIdMovie(@RequestParam("id") String id){
        return dislikedMovieService.getAllCountByIdMovie(Long.valueOf(id));
    }
    @GetMapping("/count/favorite")
    public String getAllCountFavoriteByIdMovie(@RequestParam("id") String id){
        return favoriteMovieService.getAllCountByIdMovie(Long.valueOf(id));
    }
    @GetMapping("/count/watched")
    public String getAllCountWatchedByIdMovie(@RequestParam("id") String id){
        return watchedMovieService.getAllCountByIdMovie(Long.valueOf(id));
    }
    @GetMapping("/count/towatch")
    public String getAllCountToWatchByIdMovie(@RequestParam("id") String id){
        return toWatchMovieService.getAllCountByIdMovie(Long.valueOf(id));
    }

}
