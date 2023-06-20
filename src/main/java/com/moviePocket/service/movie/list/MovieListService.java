package com.moviePocket.service.movie.list;

import com.moviePocket.entities.movie.list.ParsMovieList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieListService {

    ResponseEntity<Void> setMovieList(String email, String title, String content);

    ResponseEntity<Void> updateMovieListTitle(String email, Long idMovieList, String title);

    ResponseEntity<Void> updateMovieListContent(String email, Long idMovieList, String content);

    ResponseEntity<Void> deleteMovieList(String email, Long idMovieList);

    ResponseEntity<List<ParsMovieList>> getAllByTitle(String title);

    ResponseEntity<List<ParsMovieList>> getMovieList(Long idMovieList);

    ResponseEntity<List<ParsMovieList>> getAllList();

    ResponseEntity<List<ParsMovieList>> getAllMyList(String email);

    ResponseEntity<List<ParsMovieList>> getAllByUsernameList(String username);

}
