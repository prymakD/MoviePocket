package com.moviePocket.controller.movie.list;


import com.moviePocket.entities.movie.list.ParsMovieList;
import com.moviePocket.service.movie.list.CategoriesMovieListService;
import com.moviePocket.service.movie.list.LikeListService;
import com.moviePocket.service.movie.list.MovieInListService;
import com.moviePocket.service.movie.list.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies/list")
@Controller
public class MovieListController {

    @Autowired
    MovieListService movieListService;
    @Autowired
    MovieInListService movieInListService;
    @Autowired
    LikeListService likeListService;
    @Autowired
    private CategoriesMovieListService categoriesMovieListService;

    @PostMapping("/set")
    public void setNewMovieList(@RequestParam("title") String title,
                                @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieListService.setMovieLis(authentication.getName(), title, content);
    }

    @PostMapping("/up")
    public void setUpdateMovieList(@RequestParam("id") Long id,
                                   @RequestParam("title") String title,
                                   @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieListService.updateMovieLis(authentication.getName(), id, title, content);
    }

    @PostMapping("/del")
    public void delMovieList(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieListService.deleteMovieLis(authentication.getName(), id);
    }

    @GetMapping("/get")
    public ParsMovieList getMovieList(@RequestParam("id") Long id) {
        return movieListService.getMovieList(id);
    }

    @PostMapping("/setmovie")
    public void setOrDelMovieInMovieList(@RequestParam("idList") Long idList, @RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieInListService.addOrDelMovieFromList(authentication.getName(), idList, idMovie);
    }

    @PostMapping("/setlike")
    public void setLikeOrDesMovieList(@RequestParam("idList") Long idList, @RequestParam("like") Boolean like) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        likeListService.setLikeOrDisOrDel(authentication.getName(), idList, like);
    }

    @PostMapping("/setcategory")
    public void setCategoryMovieList(@RequestParam("idList") Long idList, @RequestParam("idCategory") Long idCategory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        categoriesMovieListService.setOrDelCategoryList(authentication.getName(), idList, idCategory);
    }

}
