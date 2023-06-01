package com.moviePocket.Service;

import java.util.List;

public interface WatchedMovieService{

    void setNewWatched(String email, Long idMovie);
    void removeFromWatched(String email, Long idMovie);
    boolean getFromWatched(String email, Long idMovie);
    List<Long> getAllUserWatched(String email);
    String getAllCountByIdMovie(Long idMovie);

}
