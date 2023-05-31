package com.moviePocket.controller;


import com.moviePocket.Service.LikeMovieReviewService;
import com.moviePocket.Service.MovieReviewService;
import com.moviePocket.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/movies/review")
@Controller
public class MovieReviewController {

    @Autowired
    MovieReviewService movieReviewService;
    @Autowired
    LikeMovieReviewService likeMovieReviewService;

    @PostMapping("/set")
    public void setMovieReview(@RequestParam("id") Long idMovie,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("content")String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieReviewService.creatMovieReview(authentication.getName(),idMovie,title,content);
    }

    @PostMapping("/up")
    public void setUpdateMovieReview(@RequestParam("id")Long idMovie,
                               @RequestParam("title") String title,
                               @RequestParam("content")String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        movieReviewService.updateMovieReview(idMovie,authentication.getName(),title,content);
    }

    @GetMapping("/get")
    public Review getByIdReview(@RequestParam("id") Long id){
        return movieReviewService.getByIDMovieReview(id);
    }

    @GetMapping("/getallmovie")
    public List<Review> getAllReviewByIdMovie(@RequestParam("id") Long id){
        return movieReviewService.getAllByIDMovie(id);
    }

    @GetMapping("/getalluser")
    public List<Review> getAllReviewByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.getAllByUser(authentication.getName());
    }

    @PostMapping("/del")
    public boolean delMovieReview(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.delMovieReview(id,authentication.getName());
    }

    @PostMapping("/setlike")
    public boolean setLike(@RequestParam("id") Long id,@RequestParam("like") int like){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.setLikeOrDis(authentication.getName(),id,like);
    }

    @PostMapping("/dellike")
    public boolean setLike(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.delLikeOrDis(authentication.getName(),id);
    }

    @GetMapping("/getlike")
    public int getLike(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.getLickOrDis(authentication.getName(),id);
    }





}
