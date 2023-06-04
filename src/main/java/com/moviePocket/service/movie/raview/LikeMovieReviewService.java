package com.moviePocket.service.movie.raview;

public interface LikeMovieReviewService {

    void setLikeOrDisOrDel(String username, Long id, boolean likeOrDis);

    boolean[] getLikeOrDis(String username, Long id);

    int[] getAllLikeAndDisByIdMovieReview(Long idReview);

}
