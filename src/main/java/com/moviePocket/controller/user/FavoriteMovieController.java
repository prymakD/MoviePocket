package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.FavoriteMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/favorite")
@Api(value = "Favorite Movie Controller", tags = "Controller for favorites movies list")
public class FavoriteMovieController {

    @Autowired
    FavoriteMovieService favoriteMoviesService;

    @PostMapping("/set")
    @ApiOperation(value = "Set or delete a movie from the favorite list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully set or deleted the movie"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public void setOrDeleteFavoriteMovie(@RequestParam Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        favoriteMoviesService.setOrDeleteNewFavoriteMovies(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Check if a user has favorited a movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the result"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public boolean getIsUserFavoriteMovie(@RequestParam Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getFromFavoriteMovies(authentication.getName(), idMovie);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all movies user's favorite list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Long> allUserFavoriteMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getAllUserFavoriteMovies(authentication.getName());
    }
}