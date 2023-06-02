package com.moviePocket.service.impl.movie;

import com.moviePocket.entities.Review;
import com.moviePocket.entities.User;
import com.moviePocket.entities.movie.ReviewMovie;
import com.moviePocket.repository.MovieReviewRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieReviewRepository movieReviewRepository;

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


    public boolean delMovieReview(Long idMovieReview, String username){
        User user = userRepository.findByEmail(username);
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if(user!=null && movieReview != null && movieReview.getUser()==user){
            movieReviewRepository.delete(movieReview);
            return true;
        }
        return false;
    }


    public Review getByIDMovieReview(Long idMovieReview){
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        return new Review(
                movieReview.getTitle(),
                movieReview.getContent(),
                movieReview.getUser().getUsername(),
                movieReview.getCreated(),
                movieReview.getUpdated(),
                movieReview.getId(),
                movieReview.getId()
        );
    }


    private List<Review> parsMovieReview(List<ReviewMovie> movieReviewList) {
        List<Review> reviewList = new ArrayList<>();
        for (ReviewMovie movieReview : movieReviewList) {
            reviewList.add(new Review(
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



    public List<Review> getAllByIDMovie(Long idMovie){
        return parsMovieReview(movieReviewRepository.getAllByIdMovie(idMovie));
    }
    public List<Review> getAllByUserAndIdMovie(String email, Long idMovie){
        User user = userRepository.findByEmail(email);
        if(user!=null)
            return parsMovieReview(movieReviewRepository.getAllByUserAndIdMovie(user,idMovie));
        return null;
    }

    public List<Review> getAllByUser(String email){
        User user = userRepository.findByEmail(email);
        if(user!=null){
            List<ReviewMovie> movieReviewList = movieReviewRepository.getAllByUser(user);
            return parsMovieReview(movieReviewList);
        }
        return null;
    }



    public int getAllCountByIdMovie(Long idMovie){
        return movieReviewRepository.getAllCountByIdMovie(idMovie);
    }

}
