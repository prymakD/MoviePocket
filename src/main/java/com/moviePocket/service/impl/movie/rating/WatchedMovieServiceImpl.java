package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.WatchedMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.rating.WatchedMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.WatchedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WatchedMovieServiceImpl implements WatchedMovieService {

    private final WatchedMovieRepository watchedMovieRepository;

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> setOrDeleteNewWatched(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        WatchedMovie watchedMovie = watchedMovieRepository.findByUserAndIdMovie(
                user, idMovie);
        if (watchedMovie == null) {
            watchedMovieRepository.save(
                    new WatchedMovie(userRepository.findByEmail(email), idMovie));
        } else {
            watchedMovieRepository.delete(watchedMovie);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Boolean> getFromWatched(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        WatchedMovie watched = watchedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return ResponseEntity.ok(watched != null);
    }

    public ResponseEntity<List<Long>> getAllUserWatched(String email) {
        List<WatchedMovie> watchedList = watchedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (WatchedMovie watched : watchedList) {
            listIdMovie.add(watched.getIdMovie());
        }
        if (listIdMovie.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(listIdMovie);
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        int count = watchedMovieRepository.getAllCountByIdMovie(idMovie);
        return ResponseEntity.ok(count);
    }
}
