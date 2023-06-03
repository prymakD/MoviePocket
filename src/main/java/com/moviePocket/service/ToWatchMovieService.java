package com.moviePocket.service;

import java.util.List;

public interface ToWatchMovieService {

    boolean setNewToWatch(String email, Long idMovie);
    boolean removeFromToWatch(String email, Long idMovie);
    boolean getFromToWatch(String email, Long idMovie);
    List<Long> getAllUserToWatch(String email);

    int getAllCountByIdMovie(Long idMovie);

}
