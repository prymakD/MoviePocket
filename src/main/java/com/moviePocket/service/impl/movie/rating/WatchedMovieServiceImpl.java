package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.WatchedMovie;
import com.moviePocket.repository.movie.rating.WatchedMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.WatchedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WatchedMovieServiceImpl implements WatchedMovieService {

    private final WatchedMovieRepository watchedMovieRepository;

    private final UserRepository userRepository;

    public void setOrDeleteNewWatched(String email, Long idMovie) {
        WatchedMovie watchedMovie = watchedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (watchedMovie == null) {
            watchedMovieRepository.save(
                    new WatchedMovie(userRepository.findByEmail(email), idMovie));
        } else {
            watchedMovieRepository.delete(watchedMovie);
        }
    }
    public boolean getFromWatched(String email, Long idMovie) {
        WatchedMovie watched = watchedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return watched != null;
    }

    public List<Long> getAllUserWatched(String email) {
        List<WatchedMovie> watchedList = watchedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (WatchedMovie watched : watchedList) {
            listIdMovie.add(watched.getIdMovie());
        }
        return listIdMovie;
    }

    public int getAllCountByIdMovie(Long idMovie) {
        return watchedMovieRepository.getAllCountByIdMovie(idMovie);
    }
}
