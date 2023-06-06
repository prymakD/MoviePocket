package com.moviePocket.service.movie.rating;

import java.util.List;

public interface DislikedMovieService {

    void setOrDeleteDislikedMovie(String email, Long idMovie);

    boolean getFromDislikedMovie(String email, Long idMovie);
    List<Long> getAllUserDislikedMovie(String email);

    int getAllCountByIdMovie(Long idMovie);

}