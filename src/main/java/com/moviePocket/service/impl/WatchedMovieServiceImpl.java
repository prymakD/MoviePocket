package com.moviePocket.service.impl;

import com.moviePocket.entities.movie.WatchedMovie;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.repository.WatchedMovieRepository;
import com.moviePocket.service.WatchedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WatchedMovieServiceImpl implements WatchedMovieService {

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;
    @Autowired
    private UserRepository userRepository;


    public boolean setNewWatched(String email, Long idMovie){
        if(watchedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email),idMovie)==null) {
            watchedMovieRepository.save(
                    new WatchedMovie(userRepository.findByEmail(email), idMovie));
            return true;
        }
        return false;
    }

    public void removeFromWatched(String email, Long idMovie){
        watchedMovieRepository.delete(
                watchedMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
    }

    public boolean getFromWatched(String email, Long idMovie) {
        WatchedMovie watched = watchedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return watched != null;
    }

    public List<Long> getAllUserWatched(String email){
        List<WatchedMovie> watchedList = watchedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (WatchedMovie watched : watchedList) {
            listIdMovie.add(watched.getIdMovie());
        }
        return listIdMovie;
    }

    public String getAllCountByIdMovie(Long idMovie){
        return String.valueOf(watchedMovieRepository.getAllCountByIdMovie(idMovie));
    }
}
