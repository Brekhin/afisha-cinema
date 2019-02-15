package com.brekhin.gateway.web.to.in.moviesession;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class AddTimeOfSessionRequest {

    private final String timeOfSessionDate;
    private final Long movieId;
    private final int price;
    private final Long hallId;

    @JsonCreator
    public AddTimeOfSessionRequest(
            @JsonProperty("timeOfSessionDate") String timeOfSessionDate,
            @JsonProperty("movieId") Long movieId,
            @JsonProperty("price") int price,
            @JsonProperty("hallId") Long hallId) {
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
        this.price = price;
        this.hallId = hallId;
    }

    public String getTimeOfSessionDate() {
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
