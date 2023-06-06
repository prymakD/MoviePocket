package com.moviePocket.entities.movie.rating;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_favorite_movies")
public class FavoriteMovie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private long idMovie;

    public FavoriteMovie(User user, long idMovie) {
        this.user = user;
        this.idMovie = idMovie;
    }
}