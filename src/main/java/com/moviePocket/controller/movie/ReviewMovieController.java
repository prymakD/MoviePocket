package com.moviePocket.controller.movie;


import com.moviePocket.entities.movie.review.Review;
import com.moviePocket.service.movie.raview.LikeMovieReviewService;
import com.moviePocket.service.movie.raview.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/review")
@Controller
public class ReviewMovieController {

    @Autowired
    MovieReviewService movieReviewService;
    @Autowired
    LikeMovieReviewService likeMovieReviewService;

    @PostMapping("/set")
    public void setMovieReview(@RequestParam("id") Long idMovie,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content) {
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

    @GetMapping("/getallusermovie")
    public List<Review> getAllByUserAndIdMovie(@RequestParam("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.getAllByUserAndIdMovie(authentication.getName(),id);
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
        return movieReviewService.delMovieReview(id, authentication.getName());
    }

    @PostMapping("/setlike")
    public void setLike(@RequestParam("id") Long id, @RequestParam("like") boolean like) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        likeMovieReviewService.setLikeOrDisOrDel(authentication.getName(), id, like);
    }

    @GetMapping("/getLike")
    public boolean[] getLikeReviewByIdMovie(@RequestParam("id") Long idReview) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.getLikeOrDis(authentication.getName(), idReview);
    }

    @GetMapping("/getallLike")
    public int[] getAllLikeReviewByIdMovie(@RequestParam("id") Long idReview) {
        return likeMovieReviewService.getAllLikeAndDisByIdMovieReview(idReview);
    }


}
