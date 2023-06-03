package com.moviePocket.service;

import java.util.List;

public interface WatchedMovieService{

    void setOrDeleteNewWatched(String email, Long idMovie);

    boolean getFromWatched(String email, Long idMovie);
    List<Long> getAllUserWatched(String email);
    int getAllCountByIdMovie(Long idMovie);

}
