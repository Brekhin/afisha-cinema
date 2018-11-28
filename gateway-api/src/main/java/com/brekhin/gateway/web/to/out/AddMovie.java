package com.brekhin.gateway.web.to.out;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.UUID;

public class AddMovie {

    private UUID movieId;

    public AddMovie(UUID movieId) {
        this.movieId = movieId;
    }

    @JsonGetter("movieId")
    public UUID getMovieId() {
        return movieId;
    }
}
