package com.moviePocket.service.impl.movie.review;

import com.moviePocket.entities.movie.review.ParsReview;
import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.review.LikeMovieReviewRepository;
import com.moviePocket.repository.movie.review.MovieReviewRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.raview.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieReviewRepository movieReviewRepository;
    @Autowired
    private LikeMovieReviewRepository likeMovieReviewRepository;

    public ReviewMovie creatMovieReview(String username, Long idMovie, String title, String content) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            ReviewMovie movieReview = new ReviewMovie(user, idMovie, title, content);
            movieReviewRepository.save(movieReview);
            return movieReview;
        }
        return null;
    }


    public ReviewMovie updateMovieReview(Long idMovieReview, String username, String title, String content) {
        User user = userRepository.findByEmail(username);
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if(user!=null && movieReview != null && movieReview.getUser()==user){
            movieReview.setTitle(title);
            movieReview.setContent(content);
            movieReviewRepository.save(movieReview);
            return movieReview;
        }
        return null;
    }

    @Transactional
    public boolean delMovieReview(Long idMovieReview, String username){
        User user = userRepository.findByEmail(username);
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if(user!=null && movieReview != null && movieReview.getUser()==user){
            likeMovieReviewRepository.deleteAllByMovieReview(movieReview);
            movieReviewRepository.delete(movieReview);
            return true;
        }
        return false;
    }


    public ParsReview getByIDMovieReview(Long idMovieReview) {
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        return new ParsReview(
                movieReview.getTitle(),
                movieReview.getContent(),
                movieReview.getUser().getUsername(),
                movieReview.getCreated(),
                movieReview.getUpdated(),
                movieReview.getId(),
                movieReview.getId()
        );
    }


    private List<ParsReview> parsMovieReview(List<ReviewMovie> movieReviewList) {
        List<ParsReview> reviewList = new ArrayList<>();
        for (ReviewMovie movieReview : movieReviewList) {
            reviewList.add(new ParsReview(
                    movieReview.getTitle(),
                    movieReview.getContent(),
                    movieReview.getUser().getUsername(),
                    movieReview.getCreated(),
                    movieReview.getUpdated(),
                    movieReview.getIdMovie(),
                    movieReview.getId()));
        }
        return reviewList;
    }


    public List<ParsReview> getAllByIDMovie(Long idMovie) {
        return parsMovieReview(movieReviewRepository.getAllByIdMovie(idMovie));
    }

    public List<ParsReview> getAllByUserAndIdMovie(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return parsMovieReview(movieReviewRepository.getAllByUserAndIdMovie(user, idMovie));
        return null;
    }

    public List<ParsReview> getAllByUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<ReviewMovie> movieReviewList = movieReviewRepository.getAllByUser(user);
            return parsMovieReview(movieReviewList);
        }
        return null;
    }



    public int getAllCountByIdMovie(Long idMovie){
        return movieReviewRepository.getAllCountByIdMovie(idMovie);
    }

}
