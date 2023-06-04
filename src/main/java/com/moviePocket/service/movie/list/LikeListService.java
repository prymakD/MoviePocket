package com.moviePocket.service.movie.list;

public interface LikeListService {
    void setLikeOrDisOrDel(String username, Long id, boolean likeOrDis);

    boolean[] getLikeOrDis(String username, Long id);

    int[] getAllLikeAndDisByIdMovieReview(Long idMovieList);
}
