package com.moviePocket.repository;

import com.moviePocket.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);
    Movie findMovieByTitle(String title);
}
