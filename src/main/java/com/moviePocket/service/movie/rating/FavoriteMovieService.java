package com.moviePocket.service.movie.rating;

import java.util.List;

public interface FavoriteMovieService {

    void setOrDeleteNewFavoriteMovies(String email, Long idMovie);

    boolean getFromFavoriteMovies(String email, Long idMovie);
    List<Long> getAllUserFavoriteMovies(String email);

    int getAllCountByIdMovie(Long idMovie);
}
