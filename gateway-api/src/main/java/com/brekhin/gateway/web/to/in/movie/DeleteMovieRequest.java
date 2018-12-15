package com.brekhin.gateway.web.to.in.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeleteMovieRequest {

    private Long movieId;

    public DeleteMovieRequest(@JsonProperty("movieId")Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
