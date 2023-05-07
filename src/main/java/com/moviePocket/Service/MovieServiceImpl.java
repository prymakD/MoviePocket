package com.moviePocket.Service;

import com.moviePocket.controller.dto.MovieDto;
import com.moviePocket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl {

    @Autowired
    private MovieRepository movieRepository;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public MovieDto getMovieById(Long movieId) {

        MovieDto movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieDto.class);
        return new MovieDto(movieId, movieSummary.getTitle(), movieSummary.getOverview(), movieSummary.getRuntime());

    }

}
