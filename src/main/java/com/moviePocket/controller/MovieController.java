package com.moviePocket.controller;

import com.moviePocket.Service.RatingMovieService;
import com.moviePocket.Service.impl.MovieServiceImpl;
import com.moviePocket.controller.dto.MovieDto;
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

    @GetMapping("/{movieId}")
    public MovieDto getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/search/{query}")
    public List<MovieDto> getAllSearchedMovies(@PathVariable String query) {
        return movieService.searchMovie(query);
    }

    @GetMapping("/rating")
    public String s(@RequestParam("id") String id){
        return ratingMovieService.getAllMovieRating(Long.valueOf(id));
    }



}
