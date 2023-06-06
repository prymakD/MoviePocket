package com.moviePocket.entities.movie;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "movies")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float avgRating;

    private String director;

    private String genre;

    private Integer budget;

    private String cast;

    private LocalDate releaseDate;

    private String plot;

    private Integer runtime;

    private BigDecimal imdbRating;

    private Integer tmdbId;

    private String posterUrl;

    private String backdropUrl;

}
