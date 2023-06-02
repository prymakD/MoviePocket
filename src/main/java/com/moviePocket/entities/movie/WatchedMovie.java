package com.moviePocket.entities.movie;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_watched_movies")
public class WatchedMovie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private long idMovie;

    public WatchedMovie(User user, long idMovie) {
        this.user = user;
        this.idMovie = idMovie;
    }
}