package com.moviePocket.Service;

import java.util.List;

public interface DislikedMovieService {

    void setNewDislikedMovie(String email, Long idMovie);
    void removeFromDislikedMovie(String email, Long idMovie);
    boolean getFromDislikedMovie(String email, Long idMovie);
    List<Long> getAllUserDislikedMovie(String email);

}