package com.brekhin.gateway.web.to.out.movie;

import com.fasterxml.jackson.annotation.JsonGetter;

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
