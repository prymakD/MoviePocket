package com.moviePocket.service.movie.rating;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DislikedMovieService {

    ResponseEntity<Void> setOrDeleteDislikedMovie(String email, Long idMovie);

    ResponseEntity<Boolean> getFromDislikedMovie(String email, Long idMovie);

    ResponseEntity<List<Long>> getAllUserDislikedMovie(String email);

    ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie);

}