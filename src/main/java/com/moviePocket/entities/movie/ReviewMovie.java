package com.moviePocket.entities.movie;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "review_movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMovie extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private long idMovie;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
