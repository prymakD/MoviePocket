package com.moviePocket.entities.movie;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "like_review")
@Getter
@Setter
@NoArgsConstructor
public class LikeMovieReview extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "review_id")
    private ReviewMovie movieReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean lickOrDis;

    public LikeMovieReview(ReviewMovie movieReview, User user, boolean lickOrDis) {
        this.movieReview = movieReview;
        this.user = user;
        this.lickOrDis = lickOrDis;
    }
}