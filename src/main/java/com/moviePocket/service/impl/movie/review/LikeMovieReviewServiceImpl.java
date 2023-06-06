package com.moviePocket.service.impl.movie.review;


import com.moviePocket.entities.movie.review.LikeMovieReview;
import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.review.LikeMovieReviewRepository;
import com.moviePocket.repository.movie.review.MovieReviewRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.raview.LikeMovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeMovieReviewServiceImpl implements LikeMovieReviewService {
    @Autowired
    MovieReviewRepository movieReviewRepository;
    @Autowired
    LikeMovieReviewRepository likeMovieReviewRepository;
    @Autowired
    UserRepository userRepository;

    public void setLikeOrDisOrDel(String username, Long id, boolean likeOrDis) {
        ReviewMovie movieReview = movieReviewRepository.getById(id);
        User user = userRepository.findByEmail(username);
        LikeMovieReview likeMovieReview = likeMovieReviewRepository.getByUserAndMovieReview(user, movieReview);
        if (user != null) {
            if (likeMovieReview == null) {
                likeMovieReviewRepository.save(new LikeMovieReview(movieReview, user, likeOrDis));
            } else if (likeMovieReview.isLickOrDis() == likeOrDis) {
                likeMovieReviewRepository.delete(likeMovieReview);
            } else {
                likeMovieReview.setLickOrDis(likeOrDis);
                likeMovieReviewRepository.save(likeMovieReview);
            }
        }
    }

    public boolean[] getLikeOrDis(String username, Long id) {
        ReviewMovie movieReview = movieReviewRepository.getById(id);
        User user = userRepository.findByEmail(username);
        LikeMovieReview likeMovieReview = likeMovieReviewRepository.getByUserAndMovieReview(user, movieReview);
        if (likeMovieReview != null) {
            return new boolean[]{likeMovieReview.isLickOrDis()};
        } else
            return new boolean[]{};
    }

    public int[] getAllLikeAndDisByIdMovieReview(Long idReview) {
        ReviewMovie movieReview = movieReviewRepository.getById(idReview);
        if (movieReview != null) {
            return new int[]{
                    likeMovieReviewRepository.countByMovieReviewAndLickOrDisIsTrue(movieReview),
                    likeMovieReviewRepository.countByMovieReviewAndLickOrDisIsFalse(movieReview)
            };
        } else
            return null;
    }


}
