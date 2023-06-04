package com.moviePocket.repository;

import com.moviePocket.entities.User;
import com.moviePocket.entities.movie.LikeMovieReview;
import com.moviePocket.entities.movie.ReviewMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeMovieReviewRepository extends JpaRepository<LikeMovieReview, Long> {

    LikeMovieReview getByUserAndMovieReview(User user, ReviewMovie movieReview);

    @Query("SELECT COUNT(lmr) FROM LikeMovieReview lmr WHERE lmr.movieReview = :movieReview AND lmr.lickOrDis = true")
    int countByMovieReviewAndLickOrDisIsTrue(ReviewMovie movieReview);

    @Query("SELECT COUNT(lmr) FROM LikeMovieReview lmr WHERE lmr.movieReview = :movieReview AND lmr.lickOrDis = false")
    int countByMovieReviewAndLickOrDisIsFalse(ReviewMovie movieReview);


}
