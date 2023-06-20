package com.moviePocket.repository.movie.review;

import com.moviePocket.entities.movie.review.ReviewMovie;
import com.moviePocket.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public interface MovieReviewRepository extends JpaRepository<ReviewMovie, Long> {

    ReviewMovie getById(Long id);

    ArrayList<ReviewMovie> getAllByIdMovie(Long id);

    List<ReviewMovie> getAllByUser(User user);

    List<ReviewMovie> getAllByUserAndIdMovie(User user,Long idMovie);

    @Query("SELECT COUNT(u) FROM ReviewMovie u WHERE u.idMovie = :movieId")
    int getAllCountByIdMovie(@Param("movieId") Long idMovie);

    @Query("SELECT COUNT(u) FROM ReviewMovie u WHERE u.idMovie = :id_user")
    int getAllCountByUser(@Param("id_user") User user);


}
