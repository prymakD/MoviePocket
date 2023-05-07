package com.moviePocket.controller;

import com.moviePocket.Service.MovieServiceImpl;
import com.moviePocket.controller.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;


    @RequestMapping("/{movieId}")
    public MovieDto getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieById(movieId);
    }
}
