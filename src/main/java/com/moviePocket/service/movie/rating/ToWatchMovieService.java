package com.moviePocket.service.movie.rating;

import java.util.List;

public interface ToWatchMovieService {

    void setOrDeleteToWatch(String email, Long idMovie);

    boolean getFromToWatch(String email, Long idMovie);
    List<Long> getAllUserToWatch(String email);
    int getAllCountByIdMovie(Long idMovie);

}
