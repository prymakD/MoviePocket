package com.moviePocket.repository;

import com.moviePocket.entities.movie.RatingMovie;
import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingMovieRepository extends JpaRepository<RatingMovie,Long> {

    RatingMovie findByUserAndIdMovie(User user, Long idMovie);
    List<RatingMovie> findAllByUser(User user);

    @Query("SELECT AVG(rm.rating) FROM RatingMovie rm WHERE rm.idMovie = :movieId")
    Double getAverageRatingByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT COUNT(u) FROM RatingMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);

}
