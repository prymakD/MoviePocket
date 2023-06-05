package com.moviePocket.service.movie.list;

import com.moviePocket.entities.movie.list.ParsMovieList;

import java.util.List;

public interface MovieListService {

    void setMovieLis(String email, String title, String content);

    void updateMovieLis(String email, Long idMovieList, String title, String content);

    void deleteMovieLis(String email, Long idMovieList);

    List<ParsMovieList> getMovieList(Long idList);

    List<ParsMovieList> getAllList();

    List<ParsMovieList> getAllMyList(String email);

    List<ParsMovieList> getAllByUsernameList(String username);

}
