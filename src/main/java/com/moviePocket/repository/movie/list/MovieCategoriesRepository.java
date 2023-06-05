package com.moviePocket.repository.movie.list;

import com.moviePocket.entities.movie.list.MovieCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MovieCategoriesRepository extends JpaRepository<MovieCategories, Long> {

    MovieCategories getById(Long id);

    @Query("SELECT mc FROM MovieCategories mc")
    List<MovieCategories> getAllCategories();

}
