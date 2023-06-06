package com.moviePocket.service.movie.raview;

import com.moviePocket.entities.movie.review.ParsReview;
import com.moviePocket.entities.movie.review.ReviewMovie;

import java.util.List;

public interface MovieReviewService {

    ReviewMovie creatMovieReview(String username, Long idMovie, String title, String content);

    List<ParsReview> getAllByIDMovie(Long idMovie);

    List<ParsReview> getAllByUser(String email);

    boolean delMovieReview(Long idMovieReview, String username);

    int getAllCountByIdMovie(Long idMovie);

    ParsReview getByIDMovieReview(Long idMovieReview);

    List<ParsReview> getAllByUserAndIdMovie(String email, Long idMovie);

    ReviewMovie updateMovieReview(Long idMovieReview, String username, String title, String content);
}
