package com.moviePocket.entities.movie;

import com.moviePocket.entities.BaseEntity;
import com.moviePocket.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "review_movie")
@Getter @Setter @NoArgsConstructor
public class MovieReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private long idMovie;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public MovieReview(User user, long idMovie, String title, String content
    ) {
        this.user = user;
        this.idMovie = idMovie;
        this.title = title;
        this.content = content;
    }
}
