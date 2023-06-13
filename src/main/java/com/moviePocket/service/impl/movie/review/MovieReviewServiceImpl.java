package com.moviePocket.service.impl.movie.review;

import com.moviePocket.entities.movie.review.ParsReview;
import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.review.LikeMovieReviewRepository;
import com.moviePocket.repository.movie.review.MovieReviewRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.raview.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Void> creatMovieReview(String email, Long idMovie, String title, String content) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            ReviewMovie movieReview = new ReviewMovie(user, idMovie, title, content);
            movieReviewRepository.save(movieReview);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    public ResponseEntity<Void> updateMovieReview(Long idMovieReview, String username, String title, String content) {
        User user = userRepository.findByEmail(username);
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieReview.getUser() != user)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        else {
            movieReview.setTitle(title);
            movieReview.setContent(content);
            movieReviewRepository.save(movieReview);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<Void> delMovieReview(Long idMovieReview, String username) {
        User user = userRepository.findByEmail(username);
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieReview.getUser() != user)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        else {
            likeMovieReviewRepository.deleteAllByMovieReview(movieReview);
            movieReviewRepository.delete(movieReview);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    public ResponseEntity<ParsReview> getByIDMovieReview(Long idMovieReview) {
        ReviewMovie movieReview = movieReviewRepository.getById(idMovieReview);
        if (movieReview == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(new ParsReview(
                movieReview.getTitle(),
                movieReview.getContent(),
                movieReview.getUser().getUsername(),
                movieReview.getCreated(),
                movieReview.getUpdated(),
                movieReview.getId(),
                movieReview.getId()
        ));
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

    public ResponseEntity<List<ParsReview>> getAllByIDMovie(Long idMovie) {
        List<ReviewMovie> movieList = movieReviewRepository.getAllByIdMovie(idMovie);
        if (movieList.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(parsMovieReview(movieList));
    }

    public ResponseEntity<List<ParsReview>> getAllByUserAndIdMovie(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(
                parsMovieReview(movieReviewRepository.getAllByUserAndIdMovie(user, idMovie)));

    }

    public ResponseEntity<List<ParsReview>> getAllByUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            List<ReviewMovie> movieReviewList = movieReviewRepository.getAllByUser(user);
            return ResponseEntity.ok(parsMovieReview(movieReviewList));
        }
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        return ResponseEntity.ok(movieReviewRepository.getAllCountByIdMovie(idMovie));
    }

}
