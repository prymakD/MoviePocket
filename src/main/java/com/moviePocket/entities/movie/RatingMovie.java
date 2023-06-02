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
@Table(name = "user_rating_movies")
public class RatingMovie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private long idMovie;

    @Column(nullable = false)
    private int rating;

    public RatingMovie(User user, long idMovie, int rating) {
        this.user = user;
        this.idMovie = idMovie;
        this.rating = rating;
    }
}
