package com.brekhin.gateway.web.to.in.moviesession;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class AddTimeOfSessionRequest {

    private final String timeOfSession;
    private final Long movieId;
    private final int price;
    private final Long hallId;

    public AddTimeOfSessionRequest(
            @JsonProperty("timeOfSession") String timeOfSession,
            @JsonProperty("movieId") Long movieId,
            @JsonProperty("price") int price,
            @JsonProperty("hallId") Long hallId) {
        this.timeOfSession = timeOfSession;
        this.movieId = movieId;
        this.price = price;
        this.hallId = hallId;
    }

    public String getTimeOfSession() {
        return timeOfSession;
    }

    public Long getMovieId() {
        return movieId;
    }

    public int getPrice() {
        return price;
    }

    public Long getHallId() {
        return hallId;
    }
}
