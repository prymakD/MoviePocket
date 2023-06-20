package com.moviePocket.service.movie.list;

import org.springframework.http.ResponseEntity;

public interface LikeListService {
    ResponseEntity<Void> setLikeOrDisOrDel(String username, Long id, boolean likeOrDis);

    ResponseEntity<boolean[]> getLikeOrDis(String username, Long id);

    ResponseEntity<int[]> getAllLikeAndDisByIdMovieReview(Long idMovieList);
}
