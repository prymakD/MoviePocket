package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.DislikedMovie;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.rating.DislikedMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.DislikedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikedMovieServiceImpl implements DislikedMovieService {

    private final DislikedMovieRepository dislikedMovieRepository;

    private final UserRepository userRepository;

    public ResponseEntity<Void> setOrDeleteDislikedMovie(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        DislikedMovie dislikedMovie = dislikedMovieRepository.findByUserAndIdMovie(user, idMovie);
        if (dislikedMovie == null) {
            dislikedMovieRepository.save(
                    new DislikedMovie(userRepository.findByEmail(email), idMovie));
        } else {
            dislikedMovieRepository.delete(dislikedMovie);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Boolean> getFromDislikedMovie(String email, Long idMovie) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        DislikedMovie dislikedMovie = dislikedMovieRepository.findByUserAndIdMovie(
                user, idMovie);
        return ResponseEntity.ok(dislikedMovie != null);
    }

    public ResponseEntity<List<Long>> getAllUserDislikedMovie(String email) {
        List<DislikedMovie> favoriteMoviesList = dislikedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (DislikedMovie dislikedMovie : favoriteMoviesList) {
            listIdMovie.add(dislikedMovie.getIdMovie());
        }
        if (listIdMovie.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(listIdMovie);
    }

    public ResponseEntity<Integer> getAllCountByIdMovie(Long idMovie) {
        int count = dislikedMovieRepository.getAllCountByIdMovie(idMovie);
        return ResponseEntity.ok(count);
    }
}
