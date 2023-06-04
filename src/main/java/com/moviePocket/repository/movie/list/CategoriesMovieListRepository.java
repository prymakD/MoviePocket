package com.moviePocket.repository.movie.list;

import com.moviePocket.entities.movie.list.CategoriesMovieList;
import com.moviePocket.entities.movie.list.MovieCategories;
import com.moviePocket.entities.movie.list.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesMovieListRepository extends JpaRepository<CategoriesMovieList, Long> {

    CategoriesMovieList getByMovieListAndMovieCategories(MovieList movieList, MovieCategories movieCategories);

    void deleteAllByMovieList(MovieList movieList);

    List<CategoriesMovieList> getAllByMovieList(MovieList movieList);

}
