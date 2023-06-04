package com.moviePocket.entities.movie.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParsMovieList {

    private Long id;
    private String title;
    private String content;
    private List<String> categoriesList;
    private List<Long> idMovies;
    private int[] likeOrDis;
    private String username;
    private Date creat;
    private Date update;

}
