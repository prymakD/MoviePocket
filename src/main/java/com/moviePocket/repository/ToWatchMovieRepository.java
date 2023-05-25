package com.moviePocket.repository;

import com.moviePocket.entities.movie.ToWatchMovie;
import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToWatchMovieRepository extends JpaRepository<ToWatchMovie,Long> {

    ToWatchMovie findByUserAndIdMovie(User user, Long idMovie);

    List<ToWatchMovie> findAllByUser(User user);


}
