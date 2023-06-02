package com.moviePocket.entities.movie;

import com.moviePocket.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "like_review")
@Getter
@Setter
@NoArgsConstructor
public class LikeMovieReview{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ReviewMovie movieReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int lickOrDis;

    public LikeMovieReview(ReviewMovie movieReview, User user, int lickOrDis) {
        this.movieReview = movieReview;
        this.user = user;
        this.lickOrDis = lickOrDis;
    }
}