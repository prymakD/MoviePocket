package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.WatchedMovieService;
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
@RequestMapping("/user/watched")
@Api(value = "Watched Movie Controller")
public class WatchedMovieController {

    @Autowired
    WatchedMovieService watchedMovieService;

    @PostMapping("/set")
    @ApiOperation("Set or delete a movie from watched list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set or deleted the movie as watched"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void setOrDeleteMovieWatched(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        watchedMovieService.setOrDeleteNewWatched(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    @ApiOperation("Check if a movie is watched by the user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved the movie watch status"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public boolean getIsMovieWatchedByUser(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getFromWatched(
                authentication.getName(), idMovie);
    }

    @GetMapping("/allByUser")
    @ApiOperation("Get all movies watched by the user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved all watched movies"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Long> allUserMovieWatchedMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return watchedMovieService.getAllUserWatched(
                authentication.getName());
    }
}
