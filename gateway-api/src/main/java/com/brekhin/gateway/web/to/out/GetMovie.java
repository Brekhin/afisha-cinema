package com.brekhin.gateway.web.to.out;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.sql.Timestamp;

public class GetMovie {

    private Long movieId;
    private String name;
    private Timestamp rentalStartDate;
    private Timestamp rentalEndDate;
    private String genre;
    private int duration;

    public GetMovie(Long movieId, String name, Timestamp rentalStartDate, Timestamp rentalEndDate, String genre, int duration) {
        this.movieId = movieId;
        this.name = name;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.genre = genre;
        this.duration = duration;
    }

    @JsonGetter("movieId")
    public Long getMovieId() {
        return movieId;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("getRentalStartDate")
    public Timestamp getRentalStartDate() {
        return rentalStartDate;
    }

    @JsonGetter("getRentalEndDate")
    public Timestamp getRentalEndDate() {
        return rentalEndDate;
    }

    @JsonGetter("genre")
    public String getGenre() {
        return genre;
    }

    @JsonGetter("duration")
    public int getDuration() {
        return duration;
    }
}
