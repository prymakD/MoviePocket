package com.moviePocket.repository.movie.review;

import com.moviePocket.entities.movie.review.LikeMovieReview;
import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeMovieReviewRepository extends JpaRepository<LikeMovieReview, Long> {

    LikeMovieReview getByUserAndMovieReview(User user, ReviewMovie movieReview);

    void deleteAllByMovieReview(ReviewMovie movieReview);

    @Query("SELECT COUNT(lmr) FROM LikeMovieReview lmr WHERE lmr.movieReview = :movieReview AND lmr.lickOrDis = true")
    int countByMovieReviewAndLickOrDisIsTrue(ReviewMovie movieReview);

    @Query("SELECT COUNT(lmr) FROM LikeMovieReview lmr WHERE lmr.movieReview = :movieReview AND lmr.lickOrDis = false")
    int countByMovieReviewAndLickOrDisIsFalse(ReviewMovie movieReview);


}
