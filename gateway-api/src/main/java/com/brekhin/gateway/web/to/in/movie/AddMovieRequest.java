package com.brekhin.gateway.web.to.in.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

public class AddMovieRequest {

    private Long movieId;

    private String name;

    private Timestamp rentalStartDate;

    private Timestamp rentalEndDate;

    private String genre;

    private int duration;

    public AddMovieRequest(@JsonProperty("movieId") Long movieId,
                           @JsonProperty("movieName") String name,
                           @JsonProperty("rentalStartDate") Timestamp rentalStartDate,
                           @JsonProperty("rentalEndDate") Timestamp rentalEndDate,
                           @JsonProperty("genre") String genre,
                           @JsonProperty("duration") int duration) {
        this.movieId = movieId;
        this.name = name;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.genre = genre;
        this.duration = duration;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getRentalStartDate() {
        return rentalStartDate;
    }

    public Timestamp getRentalEndDate() {
        return rentalEndDate;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
