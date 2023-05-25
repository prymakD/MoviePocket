package com.moviePocket.repository;

import com.moviePocket.entities.movie.DislikedMovie;
import com.moviePocket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikedMovieRepository extends JpaRepository<DislikedMovie,Long> {

    DislikedMovie findByUserAndIdMovie(User user, Long idMovie);
    List<DislikedMovie> findAllByUser(User user);

}