package com.moviePocket.service;

import java.util.List;

public interface DislikedMovieService {

    boolean setNewDislikedMovie(String email, Long idMovie);
    void removeFromDislikedMovie(String email, Long idMovie);
    boolean getFromDislikedMovie(String email, Long idMovie);
    List<Long> getAllUserDislikedMovie(String email);

    String getAllCountByIdMovie(Long idMovie);

}