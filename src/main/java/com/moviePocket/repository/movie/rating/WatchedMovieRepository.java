package com.moviePocket.repository.movie.rating;


import com.moviePocket.entities.movie.rating.WatchedMovie;
import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WatchedMovieRepository extends JpaRepository<WatchedMovie, Long>{

    WatchedMovie findByUserAndIdMovie(User user, Long idMovie);

    List<WatchedMovie> findAllByUser(User user);

    @Query("SELECT COUNT(u) FROM WatchedMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);


}
