package com.moviePocket.Service;

import java.util.List;

public interface ToWatchMovieService {

    void setNewToWatch(String email, Long idMovie);
    void removeFromToWatch(String email, Long idMovie);
    boolean getFromToWatch(String email, Long idMovie);
    List<Long> getAllUserToWatch(String email);

}
