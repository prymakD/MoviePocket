package com.moviePocket.repository.movie.rating;

import com.moviePocket.entities.movie.rating.ToWatchMovie;
import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToWatchMovieRepository extends JpaRepository<ToWatchMovie,Long> {

    ToWatchMovie findByUserAndIdMovie(User user, Long idMovie);

    List<ToWatchMovie> findAllByUser(User user);
    @Query("SELECT COUNT(u) FROM ToWatchMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);


}
