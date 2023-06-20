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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Transactional
    public ResponseEntity<Void> setMovieList(String email, String title, String content) throws NotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        MovieList movieList = new MovieList(title, content, user);
        movieListRepository.save(movieList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> updateMovieListTitle(String email, Long idMovieList, String title) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieList.getUser() != user) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            movieList.setTitle(title);
            movieListRepository.save(movieList);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<Void> updateMovieListContent(String email, Long idMovieList, String content) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieList.getUser() != user) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            movieList.setContent(content);
            movieListRepository.save(movieList);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @Transactional
    public ResponseEntity<Void> deleteMovieList(String email, Long idMovieList) {
        User user = userRepository.findByEmail(email);
        MovieList movieList = movieListRepository.getById(idMovieList);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else if (movieList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (movieList.getUser() != user) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            movieInListRepository.deleteAllByMovieList(movieList);
            likeListRepository.deleteAllByMovieList(movieList);
            categoriesMovieListRepository.deleteAllByMovieList(movieList);
            movieListRepository.delete(movieList);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<List<ParsMovieList>> getMovieList(Long idMovieList) {
        if (movieListRepository.existsById(idMovieList)) {
            MovieList movieList = movieListRepository.getById(idMovieList);
            List<MovieList> movieLists = new ArrayList<>();
            movieLists.add(movieList);
            return ResponseEntity.ok(parsList(movieLists));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<ParsMovieList>> getAllList() {
        List<MovieList> movieList = movieListRepository.findAll();
        return ResponseEntity.ok(parsList(movieList));
    }

    public ResponseEntity<List<ParsMovieList>> getAllMyList(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            List<MovieList> movieList = movieListRepository.findAllByUser(user);
            return ResponseEntity.ok(parsList(movieList));
        }
    }

    public ResponseEntity<List<ParsMovieList>> getAllByUsernameList(String username) {
        User user = userRepository.findByUsernameAndAccountActive(username, true);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            List<MovieList> movieList = movieListRepository.findAllByUser(user);
            return ResponseEntity.ok(parsList(movieList));
        }
    }

    public ResponseEntity<List<ParsMovieList>> getAllByTitle(String title) {
        List<MovieList> movieLists = movieListRepository.findAllByTitle(title);
        return ResponseEntity.ok(parsList(movieLists));
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
