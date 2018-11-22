package com.brekhin.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @Column(name = "movie_id", nullable = false)
    private UUID movieId;

    private String name;

    @Column(name = "rental_start_date")
    private Date rentalStartDate;

    @Column(name = "rental_end_date")
    private Date rentalEndDate;

    private String genre;

    private int duration;

    public MovieEntity() {
    }

    public UUID getMovieId() {
        return movieId;
    }

    public MovieEntity setMovieId(UUID movieId) {
        this.movieId = movieId;
        return this;
    }

    public String getName() {
        return name;
    }

    public MovieEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public MovieEntity setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
        return this;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public MovieEntity setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public MovieEntity setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public MovieEntity setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
