package com.moviePocket.service.impl;

import com.moviePocket.entities.Rating;
import com.moviePocket.entities.movie.RatingMovie;
import com.moviePocket.repository.RatingMovieRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.RatingMovieService;
import com.moviePocket.service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void setNewRatingMovie(String email, Long idMovie, int rating) {
        if (ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie) == null) {
            ratingMovieRepository.save(
                    new RatingMovie(userRepository.findByEmail(email), idMovie, rating));

            watchedMovieService.setOrDeleteNewWatched(email, idMovie);
        } else {
            RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                    userRepository.findByEmail(email), idMovie);
            ratingMovie.setRating(rating);
            ratingMovieRepository.save(ratingMovie);
        }
    }

    public void removeFromRatingMovie(String email, Long idMovie) {
        RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (ratingMovie != null)
            ratingMovieRepository.delete(ratingMovie);
    }

    public int getFromRatingMovie(String email, Long idMovie) {
        RatingMovie ratingMovie = ratingMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (ratingMovie != null)
            return ratingMovie.getRating();
        return 0;
    }

    public List<Rating> getAllUserRatingMovie(String email) {
        return parsRatingMovieList(ratingMovieRepository.findAllByUser(
                userRepository.findByEmail(email)));
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

    public double getAllMovieRating(Long idMovie) {
        Double rating = ratingMovieRepository.getAverageRatingByMovieId(idMovie);
        if (rating != null) {
            BigDecimal bd = BigDecimal.valueOf(rating);
            BigDecimal roundedNumber = bd.setScale(1, RoundingMode.HALF_UP);
            return roundedNumber.doubleValue();
        }
        return 0.0;
    }

    public int getAllCountByIdMovie(Long idMovie) {
        return ratingMovieRepository.getAllCountByIdMovie(idMovie);
    }

}