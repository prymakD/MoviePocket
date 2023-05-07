package com.moviePocket.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "movies")
@Getter @Setter @NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Float avgRating;

    private String director;

    private String genre;

    private Integer budget;

    private String cast;

    private LocalDate releaseDate;

    @Lob
    private String plot;

    private Integer runtime;

    private BigDecimal imdbRating;

    @Column(nullable = false, unique = true)
    private Integer tmdbId;

    private String posterUrl;

    private String backdropUrl;

}
