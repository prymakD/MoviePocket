package com.moviePocket.service.impl.movie.list;

import com.moviePocket.entities.movie.list.CategoriesMovieList;
import com.moviePocket.entities.movie.list.MovieInList;
import com.moviePocket.entities.movie.list.MovieList;
import com.moviePocket.entities.movie.list.ParsMovieList;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.movie.list.CategoriesMovieListRepository;
import com.moviePocket.repository.movie.list.LikeListRepository;
import com.moviePocket.repository.movie.list.MovieInListRepository;
import com.moviePocket.repository.movie.list.MovieListRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.movie.list.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieListServiceImpl implements MovieListService {

    @Autowired
    private MovieListRepository movieListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieInListRepository movieInListRepository;
    @Autowired
    private LikeListRepository likeListRepository;
    @Autowired
    private CategoriesMovieListRepository categoriesMovieListRepository;

    public void setMovieLis(String email, String title, String content) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            MovieList movieList = new MovieList(title, content, user);
            movieListRepository.save(movieList);
        }
    }

    public void updateMovieLis(String email, Long idMovieList, String title, String content) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            movieList.setTitle(title);
            movieList.setContent(content);
            movieListRepository.save(movieList);
        }
    }

    @Transactional
    public void deleteMovieLis(String email, Long idMovieList) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            movieInListRepository.deleteAllByMovieList(movieList);
            likeListRepository.deleteAllByMovieList(movieList);
            categoriesMovieListRepository.deleteAllByMovieList(movieList);
            movieListRepository.delete(movieList);
        }
    }

    public List<ParsMovieList> getMovieList(Long idList) {
        if (movieListRepository.existsById(idList)) {
            MovieList movieList = movieListRepository.getById(idList);
            List<MovieList> movieLL = new ArrayList<>();
            movieLL.add(movieList);
            return parsList(movieLL);
        }
        System.out.println("hi");
        return null;
    }

    public List<ParsMovieList> getAllList() {
        List<MovieList> movieLL = movieListRepository.findAll();
        return parsList(movieLL);
    }

    public List<ParsMovieList> getAllMyList(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<MovieList> movieLL = movieListRepository.findAllByUser(user);
            return parsList(movieLL);
        }
        return null;
    }

    public List<ParsMovieList> getAllByUsernameList(String username) {
        User user = userRepository.findAllByUsername(username);
        if (user != null) {
            List<MovieList> movieLL = movieListRepository.findAllByUser(user);
            return parsList(movieLL);
        }
        return null;
    }

    public List<ParsMovieList> parsList(List<MovieList> movieLL) {
        List<ParsMovieList> parsMovieLL = new ArrayList<>();
        for (int i = 0; i < movieLL.size(); i++) {
            System.out.println(i);
            List<CategoriesMovieList> categoriesList = categoriesMovieListRepository.getAllByMovieList(movieLL.get(i));
            List<String> categoriesString = new ArrayList<>();
            for (CategoriesMovieList categoriesMovieList : categoriesList) {
                categoriesString.add(categoriesMovieList.getMovieCategories().getName());
            }
            List<MovieInList> movieListList = movieInListRepository.getAllByMovieList(movieLL.get(i));
            List<Long> idMovieList = new ArrayList<>();
            for (MovieInList movieInList : movieListList) {
                idMovieList.add(movieInList.getIdMovie());
            }
            int[] likeAndDis = new int[]{likeListRepository.countByMovieReviewAndLickOrDisIsTrue(movieLL.get(i)),
                    likeListRepository.countByMovieReviewAndLickOrDisIsFalse(movieLL.get(i))};
            ParsMovieList parsMovieList = new ParsMovieList(
                    movieLL.get(i).getId(),
                    movieLL.get(i).getTitle(),
                    movieLL.get(i).getContent(),
                    categoriesString,
                    idMovieList,
                    likeAndDis,
                    movieLL.get(i).getUser().getUsername(),
                    movieLL.get(i).getCreated(),
                    movieLL.get(i).getUpdated()
            );
            parsMovieLL.add(parsMovieList);
        }
        return parsMovieLL;
    }


}
