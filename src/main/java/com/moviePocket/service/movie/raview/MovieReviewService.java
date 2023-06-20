package com.moviePocket.service.movie.raview;

import com.moviePocket.entities.movie.review.ParsReview;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieReviewService {

    ResponseEntity<Void> creatMovieReview(String username, Long idMovie, String title, String content);

    ResponseEntity<List<ParsReview>> getAllByIDMovie(Long idMovie);

    ResponseEntity<List<ParsReview>> getAllByUser(String email);

    ResponseEntity<Void> delMovieReview(Long idMovieReview, String username);

    ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie);

    ResponseEntity<ParsReview> getByIDMovieReview(Long idMovieReview);

    ResponseEntity<List<ParsReview>> getAllByUserAndIdMovie(String email, Long idMovie);

    ResponseEntity<Void> updateMovieReview(Long idMovieReview, String username, String title, String content);
}
