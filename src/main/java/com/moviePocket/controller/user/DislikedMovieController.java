package com.moviePocket.controller.user;

import com.moviePocket.service.movie.rating.DislikedMovieService;
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
@RequestMapping("/user/dislikedMovie")
@Api(value = "Disliked Movie Controller", tags = "Controller to dislike a movie")
public class DislikedMovieController {

    @Autowired
    DislikedMovieService dislikedMovieService;

    @PostMapping("/set")
    @ApiOperation(value = "Set or delete a movie from the disliked list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully set or deleted the movie"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public void setOrDeleteMovieWatched(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        dislikedMovieService.setOrDeleteDislikedMovie(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Check if a user has disliked a movie", notes = "returns boolean")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the result"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public boolean getIsUserDislikedMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getFromDislikedMovie(authentication.getName(), idMovie);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all movies disliked by a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Long> allUserDislikedMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getAllUserDislikedMovie(authentication.getName());
    }
}