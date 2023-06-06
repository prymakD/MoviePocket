package com.moviePocket.service.impl.movie.rating;

import com.moviePocket.entities.movie.rating.DislikedMovie;
import com.moviePocket.repository.movie.rating.DislikedMovieRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.rating.DislikedMovieService;
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
        DislikedMovie dislikedMovie = dislikedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        if (dislikedMovie == null) {
            dislikedMovieRepository.save(
                    new DislikedMovie(userRepository.findByEmail(email), idMovie));
        } else {
            dislikedMovieRepository.delete(dislikedMovie);
        }
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
