package com.brekhin.gateway.web.to.in.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddMovieRequest {

    private String movieName;

    private String genre;

    private int duration;

    @JsonCreator
    public AddMovieRequest(@JsonProperty("movieName") String movieName,
                           @JsonProperty("genre") String genre,
                           @JsonProperty("duration") int duration) {
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() {
        return movieName;
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
