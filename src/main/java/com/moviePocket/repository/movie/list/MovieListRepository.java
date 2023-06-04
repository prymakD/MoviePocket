package com.moviePocket.repository.movie.list;

import com.moviePocket.entities.movie.list.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {

    MovieList getById(Long id);

}
