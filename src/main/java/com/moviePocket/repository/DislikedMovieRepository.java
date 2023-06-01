package com.moviePocket.repository;

import com.moviePocket.entities.movie.DislikedMovie;
import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DislikedMovieRepository extends JpaRepository<DislikedMovie,Long> {

    DislikedMovie findByUserAndIdMovie(User user, Long idMovie);
    List<DislikedMovie> findAllByUser(User user);

    @Query("SELECT COUNT(u) FROM DislikedMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);
}