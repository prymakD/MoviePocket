package com.moviePocket.service.impl;

import com.moviePocket.entities.movie.ToWatchMovie;
import com.moviePocket.repository.ToWatchMovieRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.ToWatchMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToWatchMovieServiceImpl implements ToWatchMovieService {

    private final ToWatchMovieRepository toWatchMovieRepository;

    private final UserRepository userRepository;

    public void setOrDeleteToWatch(String email, Long idMovie) {
        if (toWatchMovieRepository.findByUserAndIdMovie(userRepository.findByEmail(email), idMovie) == null) {
            toWatchMovieRepository.save(new ToWatchMovie(userRepository.findByEmail(email), idMovie));
        } else {
            removeFromToWatch(email, idMovie);
        }
    }

    private void removeFromToWatch(String email, Long idMovie) {
        toWatchMovieRepository.delete(
                toWatchMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email), idMovie));
    }

    public boolean getFromToWatch(String email, Long idMovie) {
        ToWatchMovie toWatchMovies = toWatchMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return toWatchMovies != null;
    }

    public List<Long> getAllUserToWatch(String email) {
        List<ToWatchMovie> toWatchList = toWatchMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (ToWatchMovie toWatch : toWatchList) {
            listIdMovie.add(toWatch.getIdMovie());
        }
        return listIdMovie;
    }

    public int getAllCountByIdMovie(Long idMovie) {
        return toWatchMovieRepository.getAllCountByIdMovie(idMovie);
    }
}
