package com.moviePocket.controller.movie.rating;

import com.moviePocket.service.movie.rating.FavoriteMovieService;
import com.moviePocket.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Favorite Movie Controller", tags = "Controller for favorites movies list")
@RequestMapping("/movies/favorite")
@CrossOrigin(origins = Utils.CORS_HOST)
public class FavoriteMovieController {

    @Autowired
    FavoriteMovieService favoriteMoviesService;

    @ApiOperation(value = "Set or delete a movie from the favorite list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully set or deleted the movie"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    @PostMapping("/set")
    public ResponseEntity<Void> setOrDeleteFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.setOrDeleteNewFavoriteMovies(authentication.getName(), idMovie);
    }

    @ApiOperation(value = "Check if a user has favorited a movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the result"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    @GetMapping("/get")
    public ResponseEntity<Boolean> getIsUserFavoriteMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getFromFavoriteMovies(
                authentication.getName(), idMovie);
    }

    @ApiOperation(value = "Get all movies user's favorite list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authentificated")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Long>> allUserFavoriteMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return favoriteMoviesService.getAllUserFavoriteMovies(
                authentication.getName());
    }

    @GetMapping("/count/favorite")
    public ResponseEntity<Integer> getAllCountFavoriteByIdMovie(@RequestParam("id") Long id) {
        return favoriteMoviesService.getAllCountByIdMovie(id);
    }

}