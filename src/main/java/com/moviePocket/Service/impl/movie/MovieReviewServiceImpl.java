package com.moviePocket.Service.impl.movie;

import com.moviePocket.Service.MovieReviewService;
import com.moviePocket.entities.movie.MovieReview;
import com.moviePocket.entities.Review;
import com.moviePocket.entities.User;
import com.moviePocket.repository.MovieReviewRepository;
import com.moviePocket.repository.UserRepository;
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

    public MovieReview creatMovieReview(String username, Long idMovie, String title, String content){
        User user = userRepository.findByEmail(username);
        if(user!=null){
            MovieReview movieReview = new MovieReview(user,idMovie,title,content);
            movieReviewRepository.save(movieReview);
            return movieReview;
        }
        return null;
    }


    public MovieReview updateMovieReview(Long idMovieReview, String username, String title, String content){
        User user = userRepository.findByEmail(username);
        if(user!=null){
            MovieReview movieReview = movieReviewRepository.getById(idMovieReview);
            movieReview.setTitle(title);
            movieReview.setContent(content);
            movieReviewRepository.save(movieReview);
            return movieReview;
        }
        return null;
    }


    public boolean delMovieReview(Long idMovieReview, String username){
        User user = userRepository.findByEmail(username);
        if(user!=null){
            MovieReview movieReview = movieReviewRepository.getById(idMovieReview);
            movieReviewRepository.delete(movieReview);
            return true;
        }
        return false;
    }


    public Review getByIDMovieReview(Long idMovieReview){
        MovieReview movieReview = movieReviewRepository.getById(idMovieReview);
        return new Review(
                movieReview.getTitle(),
                movieReview.getContent(),
                movieReview.getUser().getUsername(),
                movieReview.getCreated(),
                movieReview.getId(),
                movieReview.getId()
        );
    }


    private List<Review> parsMovieReview(List<MovieReview> movieReviewList){
        List<Review> reviewList = new ArrayList<>();
        for (MovieReview movieReview : movieReviewList) {
            reviewList.add(new Review(
                    movieReview.getTitle(),
                    movieReview.getContent(),
                    movieReview.getUser().getUsername(),
                    movieReview.getCreated(),
                    movieReview.getId(),
                    movieReview.getId()));
        }
        return reviewList;
    }



    public List<Review> getAllByIDMovie(Long idMovie){
        return parsMovieReview(movieReviewRepository.getAllByIdMovie(idMovie));
    }

    public List<Review> getAllByUser(String email){
        User user = userRepository.findByEmail(email);
        if(user!=null){
            List<MovieReview> movieReviewList = movieReviewRepository.getAllByUser(user);
            return parsMovieReview(movieReviewList);
        }
        return null;
    }



    public int getAllCountByIdMovie(Long idMovie){
        return movieReviewRepository.getAllCountByIdMovie(idMovie);
    }

}
