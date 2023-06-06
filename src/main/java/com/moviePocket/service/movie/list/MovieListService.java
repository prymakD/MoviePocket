package com.moviePocket.service.movie.list;

import com.moviePocket.entities.movie.list.ParsMovieList;

import java.util.List;

public interface MovieListService {

    void setMovieList(String email, String title, String content);

    void updateMovieListTitle(String email, Long idMovieList, String title);

    void updateMovieListContent(String email, Long idMovieList, String content);

    void deleteMovieList(String email, Long idMovieList);

    List<ParsMovieList> getAllByTitle(String title);

    List<ParsMovieList> getMovieList(Long idMovieList);

    List<ParsMovieList> getAllList();

    List<ParsMovieList> getAllMyList(String email);

    List<ParsMovieList> getAllByUsernameList(String username);

}
