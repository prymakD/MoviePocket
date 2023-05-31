package com.moviePocket.Service;

import com.moviePocket.entities.movie.MovieReview;
import com.moviePocket.entities.Review;

import java.util.List;

public interface MovieReviewService {

    MovieReview creatMovieReview(String username, Long idMovie, String title, String content);

    List<Review> getAllByIDMovie(Long idMovie);
    List<Review> getAllByUser(String email);
    boolean delMovieReview(Long idMovieReview, String username);
    int getAllCountByIdMovie(Long idMovie);
    Review getByIDMovieReview(Long idMovieReview);
    MovieReview updateMovieReview(Long idMovieReview, String username, String title, String content);
}
