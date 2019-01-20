package com.brekhin.gateway.web.to.in.moviesession;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class AddTimeOfSessionRequest {

    private final Long timeOfSessionId;
    private final Timestamp timeOfSessionDate;
    private final Long movieId;
    private final int price;
    private final Long hallId;

    public AddTimeOfSessionRequest(
            @JsonProperty("timeOfSessionId") Long timeOfSessionId,
            @JsonProperty("timeOfSessionDate") Timestamp timeOfSessionDate,
            @JsonProperty("movieId") Long movieId,
            @JsonProperty("price") int price,
            @JsonProperty("hallId") Long hallId) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
        this.price = price;
        this.hallId = hallId;
    }

    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
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
