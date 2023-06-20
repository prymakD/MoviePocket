package com.moviePocket.service.movie.raview;

import org.springframework.http.ResponseEntity;

public interface LikeMovieReviewService {

    ResponseEntity<Void> setLikeOrDisOrDel(String username, Long id, boolean likeOrDis);

    ResponseEntity<Boolean> getLikeOrDis(String username, Long id);

    ResponseEntity<Integer[]> getAllLikeAndDisByIdMovieReview(Long idReview);

}
