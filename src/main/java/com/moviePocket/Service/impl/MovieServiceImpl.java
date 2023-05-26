package com.moviePocket.Service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moviePocket.controller.dto.MovieDto;
import com.moviePocket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl {

    @Autowired
    private MovieRepository movieRepository;

    @Value("${api.key}")
    private  String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public MovieDto getMovieById(Long movieId) {

        MovieDto movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieDto.class);
        return new MovieDto(movieId, movieSummary.getTitle(), movieSummary.getOverview(), movieSummary.getRuntime());
    }

    public List<MovieDto> searchMovie(String query) {
        List<MovieDto> movieSearchResult = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String apiUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + query;

        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<>() {};

        Map<String, Object> responseMap = restTemplate.exchange(apiUrl, HttpMethod.GET, null, responseType).getBody();

        movieSearchResult = objectMapper.convertValue(responseMap.get("results"), new TypeReference<List<MovieDto>>() {});

        return movieSearchResult;
    }

}