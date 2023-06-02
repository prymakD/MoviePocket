package com.moviePocket.service;

public interface LikeMovieReviewService {

    boolean setLikeOrDis(String username, Long idMovie, int likeOrDis);
    boolean delLikeOrDis(String username, Long idMovie);
    int getLickOrDis(String username,Long idReview);

}
