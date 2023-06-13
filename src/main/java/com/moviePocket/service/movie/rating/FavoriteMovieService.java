package com.moviePocket.service.movie.rating;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteMovieService {

    ResponseEntity<Void> setOrDeleteNewFavoriteMovies(String email, Long idMovie);

    ResponseEntity<Boolean> getFromFavoriteMovies(String email, Long idMovie);

    ResponseEntity<List<Long>> getAllUserFavoriteMovies(String email);

    ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie);
}
