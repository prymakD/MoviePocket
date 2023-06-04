package com.moviePocket.service.movie.rating;

import com.moviePocket.entities.movie.rating.Rating;

import java.util.List;

public interface RatingMovieService {

    void setNewRatingMovie(String email, Long idMovie, int rating);

    void removeFromRatingMovie(String email, Long idMovie);

    int getFromRatingMovie(String email, Long idMovie);

    List<Rating> getAllUserRatingMovie(String email);

    double getAllMovieRating(Long idFilm);

    int getAllCountByIdMovie(Long idMovie);
}
