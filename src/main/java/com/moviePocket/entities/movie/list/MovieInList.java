package com.moviePocket.entities.movie.list;

import com.moviePocket.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie_in_list")
public class MovieInList extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idMovieList", referencedColumnName = "id")
    private MovieList movieList;

    @Column(nullable = false)
    private long idMovie;

}
