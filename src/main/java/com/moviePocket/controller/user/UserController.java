package com.moviePocket.controller.user;

import com.moviePocket.entities.user.ParsUserPage;
import com.moviePocket.entities.user.User;
import com.moviePocket.service.UserService;
import com.moviePocket.service.movie.list.MovieListService;
import com.moviePocket.service.movie.rating.DislikedMovieService;
import com.moviePocket.service.movie.rating.FavoriteMovieService;
import com.moviePocket.service.movie.rating.WatchedMovieService;
import com.moviePocket.service.movie.raview.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;
    public final MovieListService movieListService;
    public final MovieReviewService movieReviewService;
    public final FavoriteMovieService favoriteMovieService;
    public final DislikedMovieService dislikedMovieService;
    public final WatchedMovieService watchedMovieService;


    @GetMapping("/{username}")
    public ResponseEntity<ParsUserPage> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ParsUserPage parsUserPage = new ParsUserPage(
                user.getUsername(),
                user.getBio(),
                user.getCreated(),
                movieListService.getAllMyList(user.getEmail()).getBody(),
                movieReviewService.getAllByUser(user.getEmail()).getBody(),
                favoriteMovieService.getAllUserFavoriteMovies(user.getEmail()).getBody(),
                dislikedMovieService.getAllUserDislikedMovie(user.getEmail()).getBody(),
                watchedMovieService.getAllUserWatched(user.getEmail()).getBody()
        );
        return new ResponseEntity<>(parsUserPage, HttpStatus.OK);
    }
}