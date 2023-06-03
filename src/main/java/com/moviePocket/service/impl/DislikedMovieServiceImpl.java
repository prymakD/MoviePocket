package com.moviePocket.service.impl;

import com.moviePocket.entities.movie.DislikedMovie;
import com.moviePocket.repository.DislikedMovieRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.DislikedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DislikedMovieServiceImpl implements DislikedMovieService {

    private final DislikedMovieRepository dislikedMovieRepository;

    private final UserRepository userRepository;

    public void setOrDeleteDislikedMovie(String email, Long idMovie) {
        if (dislikedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie) == null) {
            dislikedMovieRepository.save(
                    new DislikedMovie(userRepository.findByEmail(email), idMovie));
        } else {
            removeFromDislikedMovie(email, idMovie);
        }
    }

    private void removeFromDislikedMovie(String email, Long idMovie) {
        dislikedMovieRepository.delete(
                dislikedMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email), idMovie));
    }

    public boolean getFromDislikedMovie(String email, Long idMovie) {
        DislikedMovie dislikedMovie = dislikedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return dislikedMovie != null;
    }

    public List<Long> getAllUserDislikedMovie(String email) {
        List<DislikedMovie> favoriteMoviesList = dislikedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (DislikedMovie dislikedMovie : favoriteMoviesList) {
            listIdMovie.add(dislikedMovie.getIdMovie());
        }
        return listIdMovie;
    }

    public int getAllCountByIdMovie(Long idMovie) {
        return dislikedMovieRepository.getAllCountByIdMovie(idMovie);
    }
}
