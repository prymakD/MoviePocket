package com.moviePocket.service.movie.list;

import java.util.List;

public interface MovieInListService {
    void addOrDelMovieFromList(String email, Long idList, Long idMovie);

    List<Long> getAllMovieFromMovieList(Long idList);

}
