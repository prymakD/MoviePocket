package com.moviePocket.entities.movie;

import com.moviePocket.entities.User;
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
@Table(name = "user_favorite_movies")
public class FavoriteMovie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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