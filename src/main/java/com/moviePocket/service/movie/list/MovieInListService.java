package com.moviePocket.service.movie.list;

import org.springframework.http.ResponseEntity;

public interface MovieInListService {
    ResponseEntity<Void> addOrDelMovieFromList(String email, Long idList, Long idMovie);

}
