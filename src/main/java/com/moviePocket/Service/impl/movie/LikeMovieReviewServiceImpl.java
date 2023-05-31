package com.moviePocket.Service.impl.movie;


import com.moviePocket.Service.LikeMovieReviewService;
import com.moviePocket.entities.movie.MovieReview;
import com.moviePocket.entities.User;
import com.moviePocket.entities.movie.LikeMovieReview;
import com.moviePocket.repository.LikeMovieReviewRepository;
import com.moviePocket.repository.MovieReviewRepository;
import com.moviePocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeMovieReviewServiceImpl implements LikeMovieReviewService {
    @Autowired
    MovieReviewRepository movieReviewRepository;
    @Autowired
    LikeMovieReviewRepository likeMovieReviewRepository;
    @Autowired
    UserRepository userRepository;

    public boolean setLikeOrDis(String username, Long id, int likeOrDis){
        MovieReview movieReview = movieReviewRepository.getById(id);
        User user = userRepository.findByEmail(username);
        if(movieReview!= null && user !=null) {
            LikeMovieReview likeMovieReview = likeMovieReviewRepository.
                    getByUserAndMovieReview(user,movieReview);
            if(likeMovieReview!=null){
                likeMovieReview.setLickOrDis(likeOrDis);
                likeMovieReviewRepository.save(likeMovieReview);
            }else{
                likeMovieReview = new LikeMovieReview(movieReview, user,likeOrDis);
                likeMovieReviewRepository.save(likeMovieReview);
            }
            return true;
        }
        return false;
    }

    public boolean delLikeOrDis(String username, Long idReview){
        MovieReview movieReview = movieReviewRepository.getById(idReview);
        User user = userRepository.findByEmail(username);
        if(movieReview!= null && user !=null) {
            LikeMovieReview likeMovieReview = likeMovieReviewRepository.
                    getByUserAndMovieReview(user, movieReview);
            if (likeMovieReview != null) {
                likeMovieReviewRepository.delete(likeMovieReview);
            }
            return true;
        }
        return false;
    }

    public int getLickOrDis(String username,Long idReview){
        User user = userRepository.findByEmail(username);
        MovieReview movieReview = movieReviewRepository.getById(idReview);
        if(user!=null && movieReview!=null){
            LikeMovieReview likeMovieReview = likeMovieReviewRepository.getByUserAndMovieReview(user,movieReview);
            return likeMovieReview.getLickOrDis();
        }
        return 0;
    }


}
