package com.brekhin.gateway.web.to.in.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

public class AddMovieRequest {

    private String name;

    private String genre;

    private int duration;

    public AddMovieRequest(@JsonProperty("movieId") Long movieId,
                           @JsonProperty("movieName") String name,
                           @JsonProperty("genre") String genre,
                           @JsonProperty("duration") int duration) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() {
        return name;
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
