package com.moviePocket.controller.movie;

import com.moviePocket.controller.dto.MovieDto;
import com.moviePocket.service.impl.movie.MovieServiceImpl;
import com.moviePocket.service.movie.rating.*;
import com.moviePocket.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Utils.CORS_HOST)
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

}
