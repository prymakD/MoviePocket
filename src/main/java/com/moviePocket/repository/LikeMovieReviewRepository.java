package com.moviePocket.repository;

import com.moviePocket.entities.movie.MovieReview;
import com.moviePocket.entities.User;
import com.moviePocket.entities.movie.LikeMovieReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeMovieReviewRepository extends JpaRepository<LikeMovieReview,Long> {

    LikeMovieReview getByUserAndMovieReview(User user, MovieReview movieReview);


}
