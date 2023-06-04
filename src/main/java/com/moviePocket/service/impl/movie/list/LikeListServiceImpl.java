package com.moviePocket.service.impl.movie.list;

import com.moviePocket.entities.movie.list.LikeList;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.list.LikeListRepository;
import com.moviePocket.repository.movie.list.MovieListRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.list.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeListServiceImpl implements LikeListService {
    @Autowired
    private LikeListRepository likeListRepository;
    @Autowired
    private MovieListRepository movieListRepository;
    @Autowired
    private UserRepository userRepository;

    public void setLikeOrDisOrDel(String username, Long id, boolean likeOrDis) {
        MovieList movieList = movieListRepository.getById(id);
        User user = userRepository.findByEmail(username);
        LikeList likeList = likeListRepository.getByUserAndMovieList(user, movieList);
        if (user != null) {
            if (likeList == null) {
                likeListRepository.save(new LikeList(movieList, user, likeOrDis));
            } else if (likeList.isLickOrDis() == likeOrDis) {
                likeListRepository.delete(likeList);
            } else {
                likeList.setLickOrDis(likeOrDis);
                likeListRepository.save(likeList);
            }
        }
    }

    public boolean[] getLikeOrDis(String username, Long id) {
        MovieList movieList = movieListRepository.getById(id);
        User user = userRepository.findByEmail(username);
        LikeList likeList = likeListRepository.getByUserAndMovieList(user, movieList);
        if (likeList != null) {
            return new boolean[]{likeList.isLickOrDis()};
        } else
            return new boolean[]{};
    }

    public int[] getAllLikeAndDisByIdMovieReview(Long idMovieList) {
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (movieList != null) {
            return new int[]{
                    likeListRepository.countByMovieReviewAndLickOrDisIsTrue(movieList),
                    likeListRepository.countByMovieReviewAndLickOrDisIsFalse(movieList)
            };
        } else
            return null;
    }


}
