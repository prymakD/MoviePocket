package com.moviePocket.entities.movie.list;

import com.moviePocket.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories_list")
public class CategoriesMovieList extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "idMovieList", referencedColumnName = "id")
    private MovieList movieList;

    @ManyToOne
    @JoinColumn(name = "idMovieCategories", referencedColumnName = "id")
    private MovieCategories movieCategories;

}

