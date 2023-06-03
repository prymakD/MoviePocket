package com.moviePocket.service.impl;

import com.moviePocket.entities.movie.FavoriteMovie;
import com.moviePocket.repository.FavoriteMovieRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.service.FavoriteMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteMovieServiceImpl implements FavoriteMovieService {


    @Autowired
    private FavoriteMovieRepository favoriteMoviesRepository;
    @Autowired
    private UserRepository userRepository;

    public void setNewFavoriteMovies(String email, Long idMovie){
        if(favoriteMoviesRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email),idMovie)==null) {

            favoriteMoviesRepository.save(
                    new FavoriteMovie(userRepository.findByEmail(email), idMovie));
        }
    }

    public void removeFromFavoriteMovies(String email, Long idMovie){
        favoriteMoviesRepository.delete(
                favoriteMoviesRepository.findByUserAndIdMovie(
                        userRepository.findByEmail(email),idMovie));
    }

    public boolean getFromFavoriteMovies(String email, Long idMovie) {
        FavoriteMovie favoriteMovie = favoriteMoviesRepository.findByUserAndIdMovie(
                userRepository.findByEmail(email), idMovie);
        return favoriteMovie != null;
    }

    public List<Long> getAllUserFavoriteMovies(String email) {
        List<FavoriteMovie> favoriteMoviesList = favoriteMoviesRepository.findAllByUser(
                userRepository.findByEmail(email));
        List<Long> listIdMovie = new ArrayList<>();
        for (FavoriteMovie favoriteMovies : favoriteMoviesList) {
            listIdMovie.add(favoriteMovies.getIdMovie());
        }
        return listIdMovie;
    }

    public int getAllCountByIdMovie(Long idMovie) {
        return favoriteMoviesRepository.getAllCountByIdMovie(idMovie);
    }

}
