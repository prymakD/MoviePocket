package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.Rating;
import com.moviePocket.entities.movie.rating.RatingMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.rating.RatingMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.RatingMovieService;
import com.moviePocket.service.movie.rating.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingMovieServiceImpl implements RatingMovieService {
    @Autowired
    private RatingMovieRepository ratingMovieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    WatchedMovieService watchedMovieService;

    public ResponseEntity<Void> setNewRatingMovie(String email, Long idMovie, int rating) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (ratingMovieRepository.findByUserAndIdMovie(user, idMovie) == null) {
            ratingMovieRepository.save(
                    new RatingMovie(userRepository.findByEmail(email), idMovie, rating));
            watchedMovieService.setOrDeleteNewWatched(email, idMovie);
        } else {
            RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                    userRepository.findByEmail(email), idMovie);
            ratingMovie.setRating(rating);
            ratingMovieRepository.save(ratingMovie);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> removeFromRatingMovie(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(user, idMovie);
        if (ratingMovie == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ratingMovieRepository.delete(ratingMovie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Integer> getFromRatingMovie(String email, Long idMovie) {
        RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (ratingMovie == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(ratingMovie.getRating());
    }

    public ResponseEntity<List<Rating>> getAllUserRatingMovie(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<RatingMovie> ratingMovieList = ratingMovieRepository.findAllByUser(user);
        if (ratingMovieList.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(parsRatingMovieList(ratingMovieList));
    }

    private List<Rating> parsRatingMovieList(List<RatingMovie> ratingMovieList) {
        List<Rating> ratingList = new ArrayList<>();
        for (RatingMovie ratingMovie : ratingMovieList) {
            ratingList.add(new Rating(
                    ratingMovie.getIdMovie(),
                    ratingMovie.getRating()
            ));
        }
        return ratingList;
    }

    public ResponseEntity<Double> getMovieRating(Long idMovie) {
        Double rating = ratingMovieRepository.getAverageRatingByMovieId(idMovie);
        if (rating != null) {
            BigDecimal bd = BigDecimal.valueOf(rating);
            BigDecimal roundedNumber = bd.setScale(1, RoundingMode.HALF_UP);
            return ResponseEntity.ok(roundedNumber.doubleValue());
        }
        return ResponseEntity.ok(0.0);
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        return ResponseEntity.ok(ratingMovieRepository.getAllCountByIdMovie(idMovie));
    }

}