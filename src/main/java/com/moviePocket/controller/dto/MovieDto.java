package com.moviePocket.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moviePocket.entities.movie.review.ParsReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {

    private Long id;
    private Double rating;
    private Integer ratingCount;
    private Integer dislikedCount;
    private Integer favoriteCount;
    private Integer toWatchCount;
    private Integer watchedCount;
    private List<ParsReview> parsReviewList;

}

