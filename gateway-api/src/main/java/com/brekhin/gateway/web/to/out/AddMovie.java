package com.brekhin.gateway.web.to.out;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.UUID;

public class AddMovie {

    private Long movieId;

    public AddMovie(Long movieId) {
        this.movieId = movieId;
    }

    @JsonGetter("movieId")
    public Long getMovieId() {
        return movieId;
    }
}
