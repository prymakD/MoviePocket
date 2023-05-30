package com.moviePocket.repository;

import com.moviePocket.entities.movie.FavoriteMovie;
import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie,Long> {

    FavoriteMovie findByUserAndIdMovie(User user, Long idMovie);
    List<FavoriteMovie> findAllByUser(User user);

    @Query("SELECT COUNT(u) FROM FavoriteMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);



}