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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieListServiceImpl implements MovieListService {


    private final MovieListRepository movieListRepository;

    private final UserRepository userRepository;

    private final MovieInListRepository movieInListRepository;

    private final LikeListRepository likeListRepository;

    private final CategoriesMovieListRepository categoriesMovieListRepository;

    public void setMovieList(String email, String title, String content) throws NotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        MovieList movieList = new MovieList(title, content, user);
        movieListRepository.save(movieList);
    }

    public void updateMovieListTitle(String email, Long idMovieList, String title) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            movieList.setTitle(title);
            movieListRepository.save(movieList);
        }
    }

    public void updateMovieListContent(String email, Long idMovieList, String content) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            movieList.setContent(content);
            movieListRepository.save(movieList);
        }
    }


    @Transactional
    public void deleteMovieList(String email, Long idMovieList) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user != null && movieList != null && movieList.getUser() == user) {
            movieInListRepository.deleteAllByMovieList(movieList);
            likeListRepository.deleteAllByMovieList(movieList);
            categoriesMovieListRepository.deleteAllByMovieList(movieList);
            movieListRepository.delete(movieList);
        }
    }

    public List<ParsMovieList> getMovieList(Long idMovieList) {
        if (movieListRepository.existsById(idMovieList)) {
            MovieList movieList = movieListRepository.getById(idMovieList);
            List<MovieList> movieLists = new ArrayList<>();
            movieLists.add(movieList);
            return parsList(movieLists);
        }
        return null;
    }

    public List<ParsMovieList> getAllList() {
        List<MovieList> movieList = movieListRepository.findAll();
        return parsList(movieList);
    }

    public List<ParsMovieList> getAllMyList(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<MovieList> movieList = movieListRepository.findAllByUser(user);
            return parsList(movieList);
        }
        return null;
    }

    public List<ParsMovieList> getAllByUsernameList(String username) {
        User user = userRepository.findAllByUsername(username);
        if (user != null) {
            List<MovieList> movieList = movieListRepository.findAllByUser(user);
            return parsList(movieList);
        }
        return null;
    }

    public List<ParsMovieList> getAllByTitle(String title) {
        List<MovieList> movieLists = movieListRepository.findAllByTitle(title);
        return parsList(movieLists);
    }

    private List<ParsMovieList> parsList(List<MovieList> movieList) {
        List<ParsMovieList> parsMovieLL = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(i);
            List<CategoriesMovieList> categoriesList = categoriesMovieListRepository.getAllByMovieList(movieList.get(i));
            List<String> categoriesString = new ArrayList<>();
            for (CategoriesMovieList categoriesMovieList : categoriesList) {
                categoriesString.add(categoriesMovieList.getMovieCategories().getName());
            }
            List<MovieInList> movieListList = movieInListRepository.getAllByMovieList(movieList.get(i));
            List<Long> idMovieList = new ArrayList<>();
            for (MovieInList movieInList : movieListList) {
                idMovieList.add(movieInList.getIdMovie());
            }
            int[] likeAndDis = new int[]{likeListRepository.countByMovieReviewAndLickOrDisIsTrue(movieList.get(i)),
                    likeListRepository.countByMovieReviewAndLickOrDisIsFalse(movieList.get(i))};
            ParsMovieList parsMovieList = new ParsMovieList(
                    movieList.get(i).getId(),
                    movieList.get(i).getTitle(),
                    movieList.get(i).getContent(),
                    categoriesString,
                    idMovieList,
                    likeAndDis,
                    movieList.get(i).getUser().getUsername(),
                    movieList.get(i).getCreated(),
                    movieList.get(i).getUpdated()
            );
            parsMovieLL.add(parsMovieList);
        }
        return parsMovieLL;
    }


}
