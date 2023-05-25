package com.moviePocket.Service.impl;

import com.moviePocket.Service.DislikedMovieService;
import com.moviePocket.entities.movie.DislikedMovie;
import com.moviePocket.repository.DislikedMovieRepository;
import com.moviePocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DislikedMovieServiceImpl implements DislikedMovieService {
    @Autowired
    private DislikedMovieRepository dislikedMovieRepository;
    @Autowired
    private UserRepository userRepository;

    public void setNewDislikedMovie(String email, Long idMovie){
        if(dislikedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email),idMovie)==null) {

            dislikedMovieRepository.save(
                    new DislikedMovie(userRepository.findByEmail(email), idMovie));
        }
    }

    public void removeFromDislikedMovie(String email, Long idMovie){
        dislikedMovieRepository.delete(
                dislikedMovieRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
    }

    public boolean getFromDislikedMovie(String email, Long idMovie) {
        DislikedMovie favoriteMovie = dislikedMovieRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return favoriteMovie != null;
    }

    public List<Long> getAllUserDislikedMovie(String email){
        List<DislikedMovie> favoriteMoviesList = dislikedMovieRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (DislikedMovie dislikedMovie : favoriteMoviesList) {
            listIdMovie.add(dislikedMovie.getId());
        }
        return listIdMovie;
    }

}
