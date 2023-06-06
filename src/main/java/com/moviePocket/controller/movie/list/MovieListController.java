package com.moviePocket.controller.movie.list;


import com.moviePocket.entities.movie.list.ParsMovieList;
import com.moviePocket.service.movie.list.CategoriesMovieListService;
import com.moviePocket.service.movie.list.LikeListService;
import com.moviePocket.service.movie.list.MovieInListService;
import com.moviePocket.service.movie.list.MovieListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies/list")
@Api(value = "Movie List Controller", description = "Controller related to the movie lists manipulations")
public class MovieListController {


    private final MovieListService movieListService;

    private final MovieInListService movieInListService;

    private final LikeListService likeListService;

    private final CategoriesMovieListService categoriesMovieListService;

    @ApiOperation(value = "Create a new movie list", notes = "Return Http response Ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created new movie list"),
            @ApiResponse(code = 403, message = "Forbidden - user is not authenticated")
    })
    @PostMapping("/set")
    public ResponseEntity<?> setNewMovieList(@RequestParam("title") String title,
                                             @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            movieListService.setMovieList(authentication.getName(), title, content);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Update movie list title", notes = "Return Http response Ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated title"),
            @ApiResponse(code = 403, message = "Forbidden - user is not authenticated"),
            @ApiResponse(code = 404, message = "Movie list not found")
    })
    @PostMapping("/updateTitle")
    public ResponseEntity<Void> setUpdateMovieListTitle(@RequestParam("idMovieList") Long idMovieList,
                                                        @RequestParam("title") String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            movieListService.updateMovieListTitle(authentication.getName(), idMovieList, title);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Update movie list content", notes = "Return Http response Ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated content"),
            @ApiResponse(code = 403, message = "Forbidden - user is not authenticated"),
            @ApiResponse(code = 404, message = "Movie list not found")
    })
    @PostMapping("/updateContent")
    public ResponseEntity<Void> setUpdateMovieListContent(@RequestParam("idMovieList") Long idMovieList,
                                                          @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        movieListService.updateMovieListContent(authentication.getName(), idMovieList, content);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete movie list and all that it had(movies in it adn likes from other 2 tables", notes = "Return Http response Ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated title"),
            @ApiResponse(code = 403, message = "Forbidden - user is not authenticated"),
            @ApiResponse(code = 404, message = "Movie list not found")
    })
    @PostMapping("/del")
    public void delMovieList(@RequestParam("idMovieList") Long idMovieList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieListService.deleteMovieList(authentication.getName(), idMovieList);
    }


    @ApiOperation(value = "Get movie list", notes = "Returns a list of movies for the given movie list ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved movie list"),
            @ApiResponse(code = 400, message = "Invalid movie list ID"),
            @ApiResponse(code = 404, message = "Movie list not found")
    })
    @GetMapping("/get")
    public ResponseEntity<List<ParsMovieList>> getMovieList(@RequestParam("idMovieList") Long idMovieList) {
        List<ParsMovieList> movieList = movieListService.getMovieList(idMovieList);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get movie list by title", notes = "Returns a list of movies that matches the title if it doesn't match it's empty list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved movie list"),
    })
    @GetMapping("/getByTitle")
    public ResponseEntity<?> getMovieListByTitle(@RequestParam("idMovieList") String title) {
        List<ParsMovieList> list = movieListService.getAllByTitle(title);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Add or delete movie from list", notes = "Adds or deletes a movie from the specified movie list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added or deleted movie from list"),
            @ApiResponse(code = 400, message = "Invalid movie list ID or movie ID"),
            @ApiResponse(code = 404, message = "Movie list or movie not found")
    })
    @PostMapping("/setMovie")
    public void setOrDelMovieInMovieList(@RequestParam("idList") Long idList, @RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieInListService.addOrDelMovieFromList(authentication.getName(), idList, idMovie);
    }

    @ApiOperation(value = "Like or dislike movie list", notes = "Likes or dislikes the specified movie list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully liked or disliked movie list"),
            @ApiResponse(code = 400, message = "Invalid movie list ID"),
            @ApiResponse(code = 404, message = "Movie list not found")
    })
    @PostMapping("/setLike")
    public void setLikeOrDesMovieList(@RequestParam("idList") Long idList, @RequestParam("like") Boolean like) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        likeListService.setLikeOrDisOrDel(authentication.getName(), idList, like);
    }

    @ApiOperation(value = "Set or delete category(tag)", notes = "Sets  or delete the category for the specified movie list that it can be searched by after")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully set category for movie list"),
            @ApiResponse(code = 400, message = "Invalid movie list ID or category ID"),
            @ApiResponse(code = 404, message = "Movie list or category not found")
    })
    @PostMapping("/setOrDeleteCategory")
    public ResponseEntity<?> setOrDelCategoryMovieList(@RequestParam("idList") Long idList, @RequestParam("idCategory") Long idCategory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            categoriesMovieListService.setOrDelCategoryList(authentication.getName(), idList, idCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Movie list not found or it's not your list", HttpStatus.NOT_FOUND);

        }
    }

    @ApiOperation(value = "Get all movie lists in the system", notes = "Returns a list of all movie lists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all movie lists")
    })
    @GetMapping("/getAllList")
    public ResponseEntity<List<ParsMovieList>> getAllList() {
        List<ParsMovieList> allLists = movieListService.getAllList();
        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all my movie lists", notes = "Returns a list of all movie lists for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all movie lists for authenticated user"),
            @ApiResponse(code = 401, message = "User not authenticated")
    })
    @GetMapping("/geTAllMyLists")
    public ResponseEntity<List<ParsMovieList>> getAllMyLists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ParsMovieList> allMyLists = movieListService.getAllMyList(authentication.getName());
        return new ResponseEntity<>(allMyLists, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all movie lists of user", notes = "Returns a list of all movie lists for the specified username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all movie lists for specified username"),
            @ApiResponse(code = 400, message = "Invalid username"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/getAllUserLists")
    public ResponseEntity<List<ParsMovieList>> getAllUsername(@RequestParam("username") String username) {
        List<ParsMovieList> allByUsernameLists = movieListService.getAllByUsernameList(username);
        return new ResponseEntity<>(allByUsernameLists, HttpStatus.OK);
    }

    @ApiOperation(value = "Get top 5 movie lists in the system", notes = "Returns a list of all movie lists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all movie lists")
    })
    @GetMapping("/getTopList")
    public ResponseEntity<List<ParsMovieList>> getTopList() {
        List<ParsMovieList> allLists = movieListService.getTop5List();
        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

}
