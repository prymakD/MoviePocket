package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.ToWatchMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.rating.ToWatchMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.ToWatchMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToWatchMovieServiceImpl implements ToWatchMovieService {

    private final ToWatchMovieRepository toWatchMovieRepository;

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> setOrDeleteToWatch(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ToWatchMovie toWatchMovie = toWatchMovieRepository.findByUserAndIdMovie(
                user, idMovie);
        if (toWatchMovie == null) {
            toWatchMovieRepository.save(new ToWatchMovie(userRepository.findByEmail(email), idMovie));
        } else {
            toWatchMovieRepository.delete(toWatchMovie);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Boolean> getFromToWatch(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        ToWatchMovie toWatchMovies = toWatchMovieRepository.findByUserAndIdMovie(
                user, idMovie);
        return ResponseEntity.ok(toWatchMovies != null);
    }

    public ResponseEntity<List<Long>> getAllUserToWatch(String email) {
        List<ToWatchMovie> toWatchList = toWatchMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (ToWatchMovie toWatch : toWatchList) {
            listIdMovie.add(toWatch.getIdMovie());
        }
        if (listIdMovie.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(listIdMovie);
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        int count = toWatchMovieRepository.getAllCountByIdMovie(idMovie);
        return ResponseEntity.ok(count);
    }
}
