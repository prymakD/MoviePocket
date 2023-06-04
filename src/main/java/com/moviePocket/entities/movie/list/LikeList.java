package com.moviePocket.entities.movie.list;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.user.User;
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
@Table(name = "like_list")
public class LikeList extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "movieList_id")
    private MovieList movieList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean lickOrDis;
}
