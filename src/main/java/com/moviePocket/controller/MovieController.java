package com.moviePocket.controller;

import com.moviePocket.controller.dto.MovieDto;
import com.moviePocket.service.*;
import com.moviePocket.service.impl.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;
    private final RatingMovieService ratingMovieService;
    private final DislikedMovieService dislikedMovieService;
    private final FavoriteMovieService favoriteMovieService;
    private final WatchedMovieService watchedMovieService;
    private final ToWatchMovieService toWatchMovieService;

    @GetMapping("/{movieId}")
    public MovieDto getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/search/{query}")
    public List<MovieDto> getAllSearchedMovies(@PathVariable String query) {
        return movieService.searchMovie(query);
    }

    @GetMapping("/rating")
    public double getMovieRating(@RequestParam("id") Long id) {
        return ratingMovieService.getAllMovieRating(id);
    }

    @GetMapping("/count/rating")
    public int getCountMovieRating(@RequestParam("id") Long id) {
        return ratingMovieService.getAllCountByIdMovie(id);
    }

    @GetMapping("/count/dislike")
    public int getAllCountDislikedByIdMovie(@RequestParam("id") Long id) {
        return dislikedMovieService.getAllCountByIdMovie(id);
    }

    @GetMapping("/count/favorite")
    public int getAllCountFavoriteByIdMovie(@RequestParam("id") Long id) {
        return favoriteMovieService.getAllCountByIdMovie(id);
    }

    @GetMapping("/count/watched")
    public int getAllCountWatchedByIdMovie(@RequestParam("id") Long id) {
        return watchedMovieService.getAllCountByIdMovie(id);
    }

    @GetMapping("/count/towatch")
    public int getAllCountToWatchByIdMovie(@RequestParam("id") Long id) {
        return toWatchMovieService.getAllCountByIdMovie(id);
    }

}
