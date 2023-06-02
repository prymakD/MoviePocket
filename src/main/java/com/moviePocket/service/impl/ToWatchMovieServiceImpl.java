package com.moviePocket.service.impl;

import com.moviePocket.entities.movie.ToWatchMovie;
import com.moviePocket.repository.ToWatchMovieRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.ToWatchMovieService;
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

    public boolean setNewToWatch(String email, Long idMovie){
        if(toWatchMovieRepository.findByUserAndIdMovie(userRepository.findByEmail(email),idMovie)==null) {
            toWatchMovieRepository.save(new ToWatchMovie(userRepository.findByEmail(email), idMovie));
            return true;
        }
        return false;
    }

    public boolean removeFromToWatch(String email, Long idMovie){
        toWatchMovieRepository.delete(
                toWatchMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
        return false;
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
    public String getAllCountByIdMovie(Long idMovie){
        return String.valueOf(toWatchMovieRepository.getAllCountByIdMovie(idMovie));
    }
}
