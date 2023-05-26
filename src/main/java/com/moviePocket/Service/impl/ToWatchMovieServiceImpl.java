package com.moviePocket.Service.impl;

import com.moviePocket.Service.ToWatchMovieService;
import com.moviePocket.entities.movie.ToWatchMovie;
import com.moviePocket.repository.ToWatchMovieRepository;
import com.moviePocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToWatchMovieServiceImpl implements ToWatchMovieService {

    @Autowired
    private ToWatchMovieRepository toWatchMovieRepository;
    @Autowired
    private UserRepository userRepository;

    public void setNewToWatch(String email, Long idMovie){
        if(toWatchMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email),idMovie)==null) {

            toWatchMovieRepository.save(
                    new ToWatchMovie(userRepository.findByEmail(email), idMovie));
        }
    }

    public void removeFromToWatch(String email, Long idMovie){
        toWatchMovieRepository.delete(
                toWatchMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
    }

    public boolean getFromToWatch(String email, Long idMovie) {
        ToWatchMovie toWatchMovies = toWatchMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return toWatchMovies != null;
    }

    public List<Long> getAllUserToWatch(String email){
        List<ToWatchMovie> toWatchList = toWatchMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (ToWatchMovie toWatch : toWatchList) {
            listIdMovie.add(toWatch.getId());
        }
        return listIdMovie;
    }
}