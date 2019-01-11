package com.brekhin.gateway.web.to.in.moviesession;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class AddTimeOfSessionRequest {

    Logger log = LoggerFactory.getLogger(AddTimeOfSessionRequest.class);
    private final Long timeOfSessionId;
    private final Timestamp timeOfSessionDate;
    private final Long movieId;
    private final int price;

    public AddTimeOfSessionRequest(
            @JsonProperty("timeOfSessionId") Long timeOfSessionId,
            @JsonProperty("timeOfSessionDate") Timestamp timeOfSessionDate,
            @JsonProperty("movieId") Long movieId,
            @JsonProperty("price") int price) {
        log.error(Integer.toString(price));
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
        this.price = price;
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
}
