package com.moviePocket.entities.movie.list;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.user.User;
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
@Table(name = "movie_list")
public class MovieList extends BaseEntity {
    @Column(nullable = false, unique = true)
    String title;
    @Column(nullable = false)
    String content;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

}
