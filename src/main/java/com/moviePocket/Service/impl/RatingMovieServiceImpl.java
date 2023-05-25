package com.moviePocket.Service.impl;

import com.moviePocket.Service.RatingMovieService;
import com.moviePocket.Service.WatchedMovieService;
import com.moviePocket.entities.movie.RatingMovie;
import com.moviePocket.repository.RatingMovieRepository;
import com.moviePocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class RatingMovieServiceImpl implements RatingMovieService {
    @Autowired
    private RatingMovieRepository ratingMovieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    WatchedMovieService watchedMovieService;

    public void setNewRatingMovie(String email, Long idMovie, int rating){
        if(ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email),idMovie)==null) {
            ratingMovieRepository.save(
                    new RatingMovie(userRepository.findByEmail(email), idMovie,rating));

            watchedMovieService.setNewWatched(email,idMovie);
        }else {
            RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                    userRepository.findByEmail(email),idMovie);
            ratingMovie.setRating(rating);
            ratingMovieRepository.save(ratingMovie);
        }
    }

    public void removeFromRatingMovie(String email, Long idMovie){
        ratingMovieRepository.delete(
                ratingMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
    }

    public int getFromRatingMovie(String email, Long idMovie) {
        RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return ratingMovie.getRating();
    }

    public List<RatingMovie> getAllUserRatingMovie(String email){
        return ratingMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
    }

    public String getAllMovieRating(Long idMovie){
        BigDecimal bd = BigDecimal.valueOf(ratingMovieRepository.getAverageRatingByMovieId(idMovie));
        BigDecimal roundedNumber = bd.setScale(1, RoundingMode.HALF_UP);
        return roundedNumber.toString();
    }

}