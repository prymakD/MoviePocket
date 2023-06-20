package com.moviePocket.service.impl.movie.review;


import com.moviePocket.entities.movie.review.LikeMovieReview;
import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.review.LikeMovieReviewRepository;
import com.moviePocket.repository.movie.review.MovieReviewRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.raview.LikeMovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LikeMovieReviewServiceImpl implements LikeMovieReviewService {
    @Autowired
    MovieReviewRepository movieReviewRepository;
    @Autowired
    LikeMovieReviewRepository likeMovieReviewRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Void> setLikeOrDisOrDel(String username, Long id, boolean likeOrDis) {
        ReviewMovie movieReview = movieReviewRepository.getById(id);
        User user = userRepository.findByEmail(username);
        LikeMovieReview likeMovieReview = likeMovieReviewRepository.getByUserAndMovieReview(user, movieReview);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        {
            if (likeMovieReview == null) {
                likeMovieReviewRepository.save(new LikeMovieReview(movieReview, user, likeOrDis));
            } else if (likeMovieReview.isLickOrDis() == likeOrDis) {
                likeMovieReviewRepository.delete(likeMovieReview);
            } else {
                likeMovieReview.setLickOrDis(likeOrDis);
                likeMovieReviewRepository.save(likeMovieReview);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<Boolean> getLikeOrDis(String username, Long id) {
        ReviewMovie movieReview = movieReviewRepository.getById(id);
        User user = userRepository.findByEmail(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (movieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        LikeMovieReview likeMovieReview = likeMovieReviewRepository.getByUserAndMovieReview(user, movieReview);
        if (likeMovieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(likeMovieReview.isLickOrDis());
    }

    public ResponseEntity<Integer[]> getAllLikeAndDisByIdMovieReview(Long idReview) {
        ReviewMovie movieReview = movieReviewRepository.getById(idReview);
        if (movieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        {
            return ResponseEntity.ok(new Integer[]{
                    likeMovieReviewRepository.countByMovieReviewAndLickOrDisIsTrue(movieReview),
                    likeMovieReviewRepository.countByMovieReviewAndLickOrDisIsFalse(movieReview)
            });
        }
    }
}
