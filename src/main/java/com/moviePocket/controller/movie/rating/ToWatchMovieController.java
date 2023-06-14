package com.moviePocket.controller.movie.rating;

import com.moviePocket.service.movie.rating.ToWatchMovieService;
import com.moviePocket.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Utils.CORS_HOST)
@RestController
@RequestMapping("/movies/towatch")
public class ToWatchMovieController {

    @Autowired
    ToWatchMovieService toWatchMovieService;

    @PostMapping("/set")
    public ResponseEntity<Void> setOrDeleteMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.setOrDeleteToWatch(authentication.getName(), id);
    }

    @GetMapping("/get")
    public ResponseEntity<Boolean> getIsUserMovieToWatch(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getFromToWatch(
                authentication.getName(), id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> allUserMovieToWatchMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return toWatchMovieService.getAllUserToWatch(
                authentication.getName());
    }

    @GetMapping("/count/towatch")
    public ResponseEntity<Integer> getAllCountToWatchByIdMovie(@RequestParam("id") Long id) {
        return toWatchMovieService.getAllCountByIdMovie(id);
    }


}
