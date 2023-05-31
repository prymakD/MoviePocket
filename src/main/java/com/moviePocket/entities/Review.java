package com.moviePocket.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Review {
    private String title;
    private String content;
    private String username;
    private Date dataCreated;
    private Long idMovie;
    private Long id;


    public Review(String title, String content, String username, Date dataCreated, Long idMovie, Long id) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.dataCreated = dataCreated;
        this.idMovie = idMovie;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Date dataCreated) {
        this.dataCreated = dataCreated;
    }
}
