package com.moviePocket.service.movie.list;

import java.util.List;

public interface CategoriesMovieListService {

    void setOrDelCategoryList(String email, Long idList, Long idCategory);

    List<Long> getAllCategoriesList(Long idList);
}
