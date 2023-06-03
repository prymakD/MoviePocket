package com.moviePocket.service;

import com.moviePocket.entities.Rating;

import java.util.List;

public interface RatingMovieService {

    void setNewRatingMovie(String email, Long idMovie, int rating);

    void removeFromRatingMovie(String email, Long idMovie);

    int getFromRatingMovie(String email, Long idMovie);

    List<Rating> getAllUserRatingMovie(String email);

    double getAllMovieRating(Long idFilm);

    int getAllCountByIdMovie(Long idMovie);
}
