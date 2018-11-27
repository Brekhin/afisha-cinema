package com.brekhin.movie.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "movies", schema = "movie_api")
public class MovieEntity {

    @Id
    @Column(name = "movie_id", nullable = false)
    private UUID movieId;

    private String name;

    @Column(name = "rental_start_date")
    private Timestamp rentalStartDate;

    @Column(name = "rental_end_date")
    private Timestamp rentalEndDate;

    private String genre;

    private int duration;

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

    public Timestamp getRentalStartDate() {
        return rentalStartDate;
    }

    public MovieEntity setRentalStartDate(Timestamp rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
        return this;
    }

    public Timestamp getRentalEndDate() {
        return rentalEndDate;
    }

    public MovieEntity setRentalEndDate(Timestamp rentalEndDate) {
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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
