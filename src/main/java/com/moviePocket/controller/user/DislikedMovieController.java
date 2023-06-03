package com.moviePocket.controller.user;

import com.moviePocket.service.DislikedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/dislikedMovie")
public class DislikedMovieController {

    @Autowired
    DislikedMovieService dislikedMovieService;

    @PostMapping("/set")
    public void setOrDeleteMovieWatched(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        dislikedMovieService.setOrDeleteDislikedMovie(authentication.getName(), idMovie);
    }

    @GetMapping("/get")
    public boolean getIsUserDislikedMovie(@RequestParam("idMovie") Long idMovie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getFromDislikedMovie(
                authentication.getName(), idMovie);
    }

    @GetMapping("/all")
    public List<Long> allUserDislikedMovies() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getAllUserDislikedMovie(
                authentication.getName());
    }

}