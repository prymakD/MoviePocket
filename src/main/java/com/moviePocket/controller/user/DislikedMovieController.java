package com.moviePocket.controller.user;

import com.moviePocket.Service.DislikedMovieService;
import com.moviePocket.Service.FavoriteMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user/dislikedmovie")
public class DislikedMovieController {

    @Autowired
    DislikedMovieService dislikedMovieService;
    @PostMapping("/set")
    public void setDislikedMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        dislikedMovieService.setNewDislikedMovie(
                authentication.getName(),id);
    }
    @PostMapping("/del")
    public void delDislikedMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        dislikedMovieService.removeFromDislikedMovie(authentication.getName(),id);
    }
    @GetMapping("/get")
    public boolean getDislikedMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getFromDislikedMovie(
                authentication.getName(),id);
    }
    @GetMapping("/all")
    public List<Long> allDislikedMovie(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return dislikedMovieService.getAllUserDislikedMovie(
                authentication.getName());
    }

}