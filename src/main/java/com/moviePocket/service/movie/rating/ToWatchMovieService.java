package com.moviePocket.service.movie.rating;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToWatchMovieService {

    ResponseEntity<Void> setOrDeleteToWatch(String email, Long idMovie);

    ResponseEntity<Boolean> getFromToWatch(String email, Long idMovie);

    ResponseEntity<List<Long>> getAllUserToWatch(String email);

    ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie);

}
