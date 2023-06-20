package com.moviePocket.service.impl.movie.list;


import com.moviePocket.entities.movie.list.CategoriesMovieList;
import com.moviePocket.entities.movie.list.MovieCategories;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.list.CategoriesMovieListRepository;
import com.moviePocket.repository.movie.list.MovieCategoriesRepository;
import com.moviePocket.repository.movie.list.MovieListRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.list.CategoriesMovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

@Service
public class CategoriesMovieListServiceImpl implements CategoriesMovieListService {
    @Autowired
    private CategoriesMovieListRepository categoriesMovieListRepository;
    @Autowired
    private MovieListRepository movieListRepository;
    @Autowired
    private MovieCategoriesRepository movieCategoriesRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<Void> setOrDelCategoryList(String email, Long idList, Long idCategory) throws NotFoundException {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idList);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (movieList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        MovieCategories movieCategories = movieCategoriesRepository.getById(idCategory);
        if (movieList.getUser() != user) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            CategoriesMovieList categoriesMovieList = categoriesMovieListRepository.
                    getByMovieListAndMovieCategories(movieList, movieCategories);
            if (categoriesMovieList != null) {
                categoriesMovieListRepository.delete(categoriesMovieList);
            } else {
                categoriesMovieListRepository.save(new CategoriesMovieList(movieList, movieCategories));
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


}