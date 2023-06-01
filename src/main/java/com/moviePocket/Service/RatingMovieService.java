package com.moviePocket.Service;

import com.moviePocket.entities.movie.RatingMovie;

import java.util.List;

public interface RatingMovieService {

    void setNewRatingMovie(String email, Long idMovie, int rating);
    void removeFromRatingMovie(String email, Long idMovie);
    int getFromRatingMovie(String email, Long idMovie);
    List<RatingMovie> getAllUserRatingMovie(String email);
    String getAllMovieRating(Long idFilm);

    String getAllCountByIdMovie(Long idMovie);
}
