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
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

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

    public void setOrDelCategoryList(String email, Long idList, Long idCategory) throws NotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        MovieList movieList = movieListRepository.getById(idList);
        if (movieList == null) {
            throw new NotFoundException("Movie list not found");
        }
        MovieCategories movieCategories = movieCategoriesRepository.getById(idCategory);
        if (!user.equals(movieList.getUser())) {
            throw new IllegalArgumentException("User does not match movie list user");
        }
        CategoriesMovieList categoriesMovieList = categoriesMovieListRepository.
                getByMovieListAndMovieCategories(movieList, movieCategories);
        if (categoriesMovieList != null) {
            categoriesMovieListRepository.delete(categoriesMovieList);
        } else {
            categoriesMovieListRepository.save(new CategoriesMovieList(movieList, movieCategories));
        }
    }


    public List<Long> getAllCategoriesList(Long idList) {
        MovieList movieList = movieListRepository.getById(idList);
        if (movieList != null) {
            List<CategoriesMovieList> categoriesList = categoriesMovieListRepository.getAllByMovieList(movieList);
            List<Long> idCategories = new ArrayList<>();
            for (CategoriesMovieList categoriesMovieList : categoriesList) {
                idCategories.add(categoriesMovieList.getMovieCategories().getId());
            }
            return idCategories;
        }
        return null;
    }


}