package com.moviePocket.repository.movie.list;

import com.moviePocket.entities.movie.list.LikeList;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LikeListRepository extends JpaRepository<LikeList, Long> {
    LikeList getByUserAndMovieList(User user, MovieList movieList);

    @Query("SELECT COUNT(lmr) FROM LikeList lmr WHERE lmr.movieList = :movieList AND lmr.lickOrDis = true")
    int countByMovieReviewAndLickOrDisIsTrue(@Param("movieList") MovieList movieList);

    @Query("SELECT COUNT(lmr) FROM LikeList lmr WHERE lmr.movieList = :movieList AND lmr.lickOrDis = false")
    int countByMovieReviewAndLickOrDisIsFalse(@Param("movieList") MovieList movieList);

    void deleteAllByMovieList(MovieList movieList);

}
