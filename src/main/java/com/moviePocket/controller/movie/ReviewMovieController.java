package com.moviePocket.controller.movie;


import com.moviePocket.entities.movie.review.ParsReview;
import com.moviePocket.service.movie.raview.LikeMovieReviewService;
import com.moviePocket.service.movie.raview.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> setMovieReview(@RequestParam("id") Long idMovie,
                                               @RequestParam("title") String title,
                                               @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.creatMovieReview(authentication.getName(), idMovie, title, content);
    }

    @PostMapping("/up")
    public ResponseEntity<Void> setUpdateMovieReview(@RequestParam("id") Long idMovie,
                                                     @RequestParam("title") String title,
                                                     @RequestParam("content") String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.updateMovieReview(idMovie, authentication.getName(), title, content);
    }

    @GetMapping("/get")
    public ResponseEntity<ParsReview> getByIdReview(@RequestParam("id") Long id) {
        return movieReviewService.getByIDMovieReview(id);
    }

    @GetMapping("/getallusermovie")
    public ResponseEntity<List<ParsReview>> getAllByUserAndIdMovie(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.getAllByUserAndIdMovie(authentication.getName(), id);
    }

    @GetMapping("/getallmovie")
    public ResponseEntity<List<ParsReview>> getAllReviewByIdMovie(@RequestParam("id") Long id) {
        return movieReviewService.getAllByIDMovie(id);
    }

    @GetMapping("/getalluser")
    public ResponseEntity<List<ParsReview>> getAllReviewByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.getAllByUser(authentication.getName());
    }

    @PostMapping("/del")
    public ResponseEntity<Void> delMovieReview(@RequestParam("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return movieReviewService.delMovieReview(id, authentication.getName());
    }

    @PostMapping("/setlike")
    public ResponseEntity<Void> setLike(@RequestParam("id") Long id, @RequestParam("like") boolean like) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.setLikeOrDisOrDel(authentication.getName(), id, like);
    }

    @GetMapping("/getLike")
    public ResponseEntity<Boolean> getLikeReviewByIdMovie(@RequestParam("id") Long idReview) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return likeMovieReviewService.getLikeOrDis(authentication.getName(), idReview);
    }

    @GetMapping("/getallLike")
    public ResponseEntity<Integer[]> getAllLikeReviewByIdMovie(@RequestParam("id") Long idReview) {
        return likeMovieReviewService.getAllLikeAndDisByIdMovieReview(idReview);
    }
}
