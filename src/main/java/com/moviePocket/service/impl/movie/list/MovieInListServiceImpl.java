package com.moviePocket.service.impl.movie.list;

import com.moviePocket.entities.movie.list.MovieInList;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.list.MovieInListRepository;
import com.moviePocket.repository.movie.list.MovieListRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.list.MovieInListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovieInListServiceImpl implements MovieInListService {
    @Autowired
    private MovieInListRepository movieInListRepository;
    @Autowired
    private MovieListRepository movieListRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> addOrDelMovieFromList(String email, Long idList, Long idMovie) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idList);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieList.getUser() != user) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            MovieInList movieInList = movieInListRepository.findByMovieListAndIdMovie(movieList, idMovie);
            if (movieInList == null) {
                movieInListRepository.save(new MovieInList(movieList, idMovie));
            } else {
                movieInListRepository.delete(movieInList);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
