package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class InfoTimeOfSessionResponse {

    private final Long timeOfSessionId;
    private final Timestamp timeOfSessionDate;
    private final Long movieId;
    private final int price;
    private final Long hallId;

    public InfoTimeOfSessionResponse(
            Long timeOfSessionId,
            Timestamp timeOfSessionDate,
            Long movieId,
            int price,
            Long hallId) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
        this.price = price;
        this.hallId = hallId;
    }

    @JsonGetter("timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    @JsonGetter("timeOfSessionDate")
    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
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
