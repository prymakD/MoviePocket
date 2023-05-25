package com.moviePocket.Service;

import java.util.List;

public interface FavoriteMovieService {

    void setNewFavoriteMovies(String email, Long idMovie);
    void removeFromFavoriteMovies(String email, Long idMovie);
    boolean getFromFavoriteMovies(String email, Long idMovie);
    List<Long> getAllUserFavoriteMovies(String email);
}
