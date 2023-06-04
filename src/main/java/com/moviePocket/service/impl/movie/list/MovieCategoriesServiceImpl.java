package com.moviePocket.service.impl.movie.list;

import com.moviePocket.entities.movie.list.MovieCategories;
import com.moviePocket.repository.movie.list.MovieCategoriesRepository;
import com.moviePocket.service.movie.list.MovieCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCategoriesServiceImpl implements MovieCategoriesService {
    @Autowired
    private MovieCategoriesRepository movieCategoriesRepository;

    public List<MovieCategories> getAllCategories() {
        return movieCategoriesRepository.getAllCategories();
    }

}
