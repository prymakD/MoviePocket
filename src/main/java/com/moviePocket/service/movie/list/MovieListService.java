package com.moviePocket.service.movie.list;

import com.moviePocket.entities.movie.list.ParsMovieList;

public interface MovieListService {

    void setMovieLis(String email, String title, String content);

    void updateMovieLis(String email, Long idMovieList, String title, String content);

    void deleteMovieLis(String email, Long idMovieList);

    ParsMovieList getMovieList(Long idList);
}
