package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class InfoTimeOfSessionResponse {

    private final Long timeOfSessionId;
    private final String timeOfSession;
    private final Long movieId;
    private final int price;
    private final Long hallId;

    public InfoTimeOfSessionResponse(
            Long timeOfSessionId,
            String timeOfSession,
            Long movieId,
            int price,
            Long hallId) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSession = timeOfSession;
        this.movieId = movieId;
        this.price = price;
        this.hallId = hallId;
    }

    @JsonGetter("timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    @JsonGetter("timeOfSession")
    public String getTimeOfSession() {
        return timeOfSession;
    }

    @JsonGetter("price")
    public int getPrice() {
        return price;
    }

    @JsonGetter("movieId")
    public Long getMovieId() {
        return movieId;
    }

    @JsonGetter("hallId")
    public Long getHallId() {
        return hallId;
    }
}
