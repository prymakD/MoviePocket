package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.FavoriteMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.rating.FavoriteMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.FavoriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private final FavoriteMovieRepository favoriteMoviesRepository;

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> setOrDeleteNewFavoriteMovies(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        FavoriteMovie favoriteMovie = favoriteMoviesRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (favoriteMovie == null) {
            favoriteMoviesRepository.save(new FavoriteMovie(userRepository.findByEmail(email), idMovie));
        } else { // if user already marked movie as fav, it will be deleted
            favoriteMoviesRepository.delete(favoriteMovie);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Boolean> getFromFavoriteMovies(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        FavoriteMovie favoriteMovie = favoriteMoviesRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return ResponseEntity.ok(favoriteMovie != null);
    }

    public ResponseEntity<List<Long>> getAllUserFavoriteMovies(String email) {
        List<FavoriteMovie> favoriteMoviesList = favoriteMoviesRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (FavoriteMovie favoriteMovies : favoriteMoviesList) {
            listIdMovie.add(favoriteMovies.getIdMovie());
        }
        if (listIdMovie.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(listIdMovie);
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        int count = favoriteMoviesRepository.getAllCountByIdMovie(idMovie);
        return ResponseEntity.ok(count);
    }

}
