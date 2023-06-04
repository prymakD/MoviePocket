package com.moviePocket.service.movie.raview;

import com.moviePocket.entities.movie.review.Review;
import com.moviePocket.entities.movie.review.ReviewMovie;

import java.util.List;

public interface MovieReviewService {

    ReviewMovie creatMovieReview(String username, Long idMovie, String title, String content);

    List<Review> getAllByIDMovie(Long idMovie);
    List<Review> getAllByUser(String email);
    boolean delMovieReview(Long idMovieReview, String username);
    int getAllCountByIdMovie(Long idMovie);
    Review getByIDMovieReview(Long idMovieReview);
    List<Review> getAllByUserAndIdMovie(String email,Long idMovie);
    ReviewMovie updateMovieReview(Long idMovieReview, String username, String title, String content);
}
