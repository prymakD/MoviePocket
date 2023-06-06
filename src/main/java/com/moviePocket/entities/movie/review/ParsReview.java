package com.moviePocket.entities.movie.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParsReview {

    private String title;
    private String content;
    private String username;
    private Date dataCreated;
    private Date dataUpdated;
    private Long idMovie;
    private Long id;

}
