package com.moviePocket.repository;

import com.moviePocket.entities.User;
import com.moviePocket.entities.movie.LikeMovieReview;
import com.moviePocket.entities.movie.ReviewMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeMovieReviewRepository extends JpaRepository<LikeMovieReview,Long> {

    LikeMovieReview getByUserAndMovieReview(User user, ReviewMovie movieReview);


}
