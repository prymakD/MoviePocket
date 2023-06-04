package com.moviePocket.repository.movie.list;

import com.moviePocket.entities.movie.list.MovieInList;
import com.moviePocket.entities.movie.list.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieInListRepository extends JpaRepository<MovieInList, Long> {

    MovieInList findByMovieListAndIdMovie(MovieList movieList, Long id);

    List<MovieInList> getAllByMovieList(MovieList movieList);

    void deleteAllByMovieList(MovieList movieList);

    boolean existsById(Long id);
}
