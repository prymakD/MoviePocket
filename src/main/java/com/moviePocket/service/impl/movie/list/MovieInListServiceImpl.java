package com.moviePocket.service.impl.movie.list;

import com.moviePocket.entities.movie.list.MovieInList;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.list.MovieInListRepository;
import com.moviePocket.repository.movie.list.MovieListRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.list.MovieInListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieInListServiceImpl implements MovieInListService {
    @Autowired
    private MovieInListRepository movieInListRepository;
    @Autowired
    private MovieListRepository movieListRepository;
    @Autowired
    private UserRepository userRepository;

    public void addOrDelMovieFromList(String email, Long idList, Long idMovie) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            MovieInList movieInList = movieInListRepository.findByMovieListAndIdMovie(movieList, idMovie);
            if (movieInList == null) {
                movieInListRepository.save(new MovieInList(movieList, idMovie));
            } else {
                movieInListRepository.delete(movieInList);
            }
        }
    }

    public List<Long> getAllMovieFromMovieList(Long idList) {
        MovieList movieList = movieListRepository.getById(idList);
        List<MovieInList> movieListList = movieInListRepository.getAllByMovieList(movieList);
        List<Long> idMovieList = new ArrayList<>();
        for (MovieInList movieInList : movieListList) {
            idMovieList.add(movieInList.getIdMovie());
        }
        return idMovieList;
    }
}
